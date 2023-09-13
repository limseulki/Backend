package com.mountain.backend.security.config;


import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


	private static final String[] PERMIT_URL_ARRAY={

		"/api/user/kakao/**",
	};

	//정적자원은 인증인가를 하지않겠다.
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

		return (web) -> web.ignoring()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations());

	}



	//비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder(){

		return new BCryptPasswordEncoder();

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {

		http.csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()));

		http.authorizeHttpRequests(request -> request
			.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
			.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/api/user/kakao/**")).permitAll()
			.requestMatchers(PathRequest.toH2Console()).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()

			.anyRequest()

			.authenticated()




		);

			http.headers().frameOptions(Customizer.withDefaults()).disable();


		http
			.cors(withDefaults());

		return http.build();

	}



}

