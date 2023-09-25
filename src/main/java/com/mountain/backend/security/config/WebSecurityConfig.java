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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(request -> request
			.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
			.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
			.requestMatchers("/api/meet/**").permitAll()
			.requestMatchers("/api/mountain/**").permitAll()
			.requestMatchers("/api/post/**").permitAll()
			.requestMatchers("/api/user/**").permitAll()

			.requestMatchers("/api/user/kakao/**").permitAll()
			.requestMatchers("/api/main/start/**").permitAll()

			.requestMatchers("/api/main/mountains").permitAll()
			.requestMatchers(PathRequest.toH2Console()).permitAll()
			.requestMatchers("/h2-console/**").permitAll()

			.anyRequest()

			.authenticated()

		);

			http.headers().frameOptions(Customizer.withDefaults()).disable();

		http
			.cors(withDefaults());

		return http.build();

	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");


		// configuration.addAllowedOrigin("http://localhost:8080");


		//모든 방식(GET, POST, PUT, DELETE 등)으로 데이터를 요청할 수 있게함

		configuration.addAllowedMethod("GET");
		configuration.addAllowedMethod("POST");
		configuration.addAllowedMethod("PUT");
		configuration.addAllowedMethod("DELETE");

		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		//이 부분은 위에서 설정한 CORS 설정을 모든 경로에 적용
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}



}

