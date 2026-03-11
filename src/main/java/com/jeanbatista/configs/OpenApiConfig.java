package com.jeanbatista.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FuelFlow API")
                        .version("v1")
                        .description("API for managing fuel stations, including fuel types, pumps, and supply records.")
                        .termsOfService("https://github.com/jean-batista/fuel-flow")
                        .license(new License()
                                .name("MIT License")
                                .url("https://github.com/jean-batista/fuel-flow")
                        )
                );
    }

}
