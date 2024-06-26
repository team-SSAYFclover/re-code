package com.clover.recode.global.config;

import java.util.Arrays;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {

    corsRegistry.addMapping("/**")
        .exposedHeaders("*")
//        .exposedHeaders("Set-Cookie")
        .allowedMethods("*")  // 모든 HTTP 메소드 허용
        .allowedOrigins("*"); // 개발 초기 전체 허용
//        .allowedOrigins("http://k10d210.p.ssafy.io", "https://k10d210.p.ssafy.io", "http://localhost:3000", "https://localhost:3000");
  }
}
