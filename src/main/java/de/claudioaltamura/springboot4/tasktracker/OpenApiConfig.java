package de.claudioaltamura.springboot4.tasktracker;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Tracker API")
                        .version("1.0.0")
                        .description("Demo project for Spring Boot 4.0 and OpenAPI spec"));
    }
}