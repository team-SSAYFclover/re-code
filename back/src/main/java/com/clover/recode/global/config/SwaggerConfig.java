package com.clover.recode.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("re:code 프로젝트의 API Document")
                        .description("re:code 프로젝트의 API 명세서입니다.")
                        .version("v0.0.1")
                );
    }

    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("All APIs")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi followApis() {
        return GroupedOpenApi.builder()
                .group("statistics APIs")
                .pathsToMatch("/statistics/**")
                .build();
    }

}

