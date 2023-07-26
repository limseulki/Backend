//package com.sparta.springsecurity.config;
//
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//public class WebSecurityConfig{
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // h2-console 사용 및 resources 접근 허용 설정
//        return (web) -> web.ignoring()
//                .requestMatchers(PathRequest.toH2Console())
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // CSRF 설정
//        http.csrf().disable();
//
//        http.authorizeRequests().anyRequest().authenticated();
//
//        // Custom 로그인 페이지 사용
//        http
//                .httpBasic()
//                .authenticationEntryPoint(customAuthenticationEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/user/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(customAccessDeniedHandler);
//
//        return http.build();
//    }
//
//}