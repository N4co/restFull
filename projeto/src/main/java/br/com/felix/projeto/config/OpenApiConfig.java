package br.com.felix.projeto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("API Restful Configuration Java 18 Spring 3.0.0-RC1")
                        .version("v1")
                        .description("Algumas informações sobre API, os links abaixo não seguem rota")
                        .termsOfService("http://localhost:8080/api/person/my-project")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("http://localhost:8080/api/person/my-project")
                        )

                );


    }
}
