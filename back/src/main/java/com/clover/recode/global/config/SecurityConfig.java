package com.clover.recode.global.config;

import com.clover.recode.domain.auth.service.CustomOAuth2UserService;
import com.clover.recode.global.jwt.JWTFilter;
import com.clover.recode.global.jwt.JWTUtil;
import com.clover.recode.global.oauth.CustomSuccessHandler;
import com.olbl.stickeymain.global.jwt.CustomLogoutFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomSuccessHandler customSuccessHandler;
  private JWTUtil jwtUtil;


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

    //oauth2
    http
        .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer
            .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                .userService(customOAuth2UserService))
            .successHandler(customSuccessHandler)
        );

    //JWTFilter 추가
    http
        .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

    http // 로그아웃 필터
        .addFilterBefore(new CustomLogoutFilter(jwtUtil), LogoutFilter.class)
        .logout(logout -> logout
            .logoutUrl("/users/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
                  // 로그아웃 성공 시, 리다이렉트 하지 않는다
                  response.setStatus(HttpServletResponse.SC_OK);
            }));

    // 경로 별 인가 작업
    http
        .authorizeHttpRequests((auth) -> auth
            // Swagger
            .requestMatchers("/actuator/prometheus").permitAll()
            .requestMatchers("/users/reissue", "/users/logout", "/users/code", "/problems/regist", "/statistics/{userId}/reviews/cnt").permitAll()
           .anyRequest().authenticated());

    http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

    // 세션 설정
    http
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http // CORS 설정
        .cors((corsCustomizer) -> corsCustomizer.configurationSource(
            request -> {
              CorsConfiguration configuration = new CorsConfiguration();

              // 허용할 출처
              configuration.setAllowedOriginPatterns(Arrays.asList("*"));

              // 허용할 HTTP 메소드
              configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

              // 자격 증명 정보 허용 설정
              configuration.setAllowCredentials(true);

              // 허용 헤더 설정
              configuration.setAllowedHeaders(Collections.singletonList("*"));

              // Pre-flight 요청 캐싱 시간 설정
              configuration.setMaxAge(3600L);

              // 브라우저에 노출할 헤더 설정
              configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "access_token"));
              return configuration;
            }));

    return http.build();
  }

}
