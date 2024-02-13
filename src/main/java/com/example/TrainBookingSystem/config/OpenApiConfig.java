package com.example.TrainBookingSystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Prototype API's", version = "v1"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfig {

}
