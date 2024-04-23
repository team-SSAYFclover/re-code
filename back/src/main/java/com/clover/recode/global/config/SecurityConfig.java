package com.clover.recode.global.config;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // csrf disable
    http
        .csrf(AbstractHttpConfigurer::disable);

    // Form 로그인 방식 disable
    http
        .formLogin(AbstractHttpConfigurer::disable);

    // http basic 인증 방식 disable
    http
        .httpBasic(AbstractHttpConfigurer::disable);

    // 경로 별 인가 작업
    http
        .authorizeHttpRequests((auth) -> auth
            // Swagger
            .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
            .requestMatchers("/**").permitAll()
            .anyRequest().permitAll());

    // 세션 설정
    http
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//    http // CORS 설정
//        .cors((corsCustomizer) -> corsCustomizer.configurationSource(
//            request -> {
//              CorsConfiguration configuration = new CorsConfiguration();
//
//              // 허용할 출처
//              configuration.setAllowedOrigins(
//                  Arrays.asList("https://k10d210.p.ssafy.io", "http://localhost:3000"));
//
//              // 허용할 HTTP 메소드
//              configuration.setAllowedMethods(Collections.singletonList("*"));
//
//              // 자격 증명 정보 허용 설정
//              configuration.setAllowCredentials(true);
//
//              // 허용 헤더 설정
//              configuration.setAllowedHeaders(Collections.singletonList("*"));
//
//              // Pre-flight 요청 캐싱 시간 설정
//              configuration.setMaxAge(3000L);
//
//              // 브라우저에 노출할 헤더 설정
//              configuration.setExposedHeaders(Arrays.asList("access", "refresh"));
//              return configuration;
//            }));

    return http.build();
  }

}
