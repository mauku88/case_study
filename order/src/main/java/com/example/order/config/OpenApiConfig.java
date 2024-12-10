package com.example.order.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
//@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class OpenApiConfig {





    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize("Order Service"));

        return new OpenAPI()

                .info(new Info().title(apiTitle).description("""
                                According to the business rules defined with this Api
                                The data in the response message indicates the data elements presented and their contents. OpenAPI 3.
                                        """)
                        .version("1.0"));

    }
}
