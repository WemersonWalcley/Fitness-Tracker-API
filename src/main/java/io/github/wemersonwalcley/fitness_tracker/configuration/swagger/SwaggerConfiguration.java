package io.github.wemersonwalcley.fitness_tracker.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApiConfiguration() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")
                                .bearerFormat("JWT")))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("fitness tracker API")
                .description("Sistema de rastreio e monitoramento de atividades físicas")
                .version("1.0")
                .contact(contact());

    }

    private Contact contact() {
        return new Contact().email("wemerson.walcley@outlook.com").name("Wemerson Walcley").url("https://github.com/WemersonWalcley");
    }
}
