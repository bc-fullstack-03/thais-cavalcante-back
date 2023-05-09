package com.sysmap.parrot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        security = {
                @SecurityRequirement(name = "RequestedBy"),
                @SecurityRequirement(name = "Authorization")
        }
)

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("Authorization", new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .name("Authorization")
                        .in(SecurityScheme.In.HEADER))
                .addSecuritySchemes("RequestedBy", new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .name("RequestedBy")
                        .in(SecurityScheme.In.HEADER)))
                .info(new Info()
                        .title("Parrot API")
                        .version("1.0.0")
                        .description("Parrot - Social Networking API Documentation"));
    }
}
