package com.DermApp.Backend.shared.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPi(
            @Value("${documentation.application.description}")
            String applicationDescription,
            @Value("${documentation.application.version}")
            String applicationVersion
    ){
        return new OpenAPI()
                .info(new Info()
                        .title("DermApp API")
                        .version(applicationVersion)
                        .description(applicationDescription)
                        .termsOfService("https://www.privacypolicies.com/live/0c6fe5e8-7fdc-4545-991a-da701a306903")
                        .license(new License()
                                .name("Apache 2.0 License")
                                .url("https://dermApp.com/license"))
                        .contact(new Contact()
                                .url("https://dermApp.com")
                                .name("Derm,.App")));
    }
}
