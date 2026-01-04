package com.onlinestore.products_service.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description("REST API for product catalog management. " +
                                "Handles product creation, updates, and provides product information to other services.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .email("rmatias.dev@gmail.com")
                                .name("Matias Alejandro Rodriguez")
                                .url("https://www.linkedin.com/in/matias-rodriguez-alejandro/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Documentation")
                        .url("https://github.com/matias-devv/online-store-products-service"))
                .servers(List.of(
                        new Server().url("http://localhost:8082").description("Development Server")
                ));
    }
}