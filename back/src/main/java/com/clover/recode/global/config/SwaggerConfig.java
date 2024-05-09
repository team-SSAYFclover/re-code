package com.clover.recode.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("re:code 프로젝트의 API Document")
                .version("v0.0.1")
                .description("re:code 프로젝트의 API 명세서입니다.");

        SecurityScheme bearer = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("Authorization")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        // Security 요청 설정
        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("Authorization");

        Components components = new Components()
                .addSecuritySchemes("Authorization", bearer);

        Server server = new Server();
        server.setUrl("https://www.recode-d210.com/api");
        Server server2 = new Server();
        server2.setUrl("http://localhost:8080/api");

        return new OpenAPI()
                .servers(List.of(server, server2))
                .components(components)
                .addSecurityItem(addSecurityItem)
                .info(info);
    }

}

