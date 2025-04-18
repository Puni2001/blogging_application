package com.blog.blogging_application.config;

// import io.swagger.v3.oas.models.ExternalDocumentation;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Contact;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.info.License;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class SwaggerConfig{

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info().title("API Title Example")
//                         .description("API Description Example")
//                         .version("API Version")
//                         .contact(new Contact()
//                                 .name("API Contact Name")
//                                 .url("https://api.contact.url")
//                                 .email("example@example.email"))
//                         .termsOfService("https://api.terms.of.service")
//                         .license(new License()
//                                 .name("API License")
//                                 .url("https://api.license.url")))
//                 .externalDocs(new ExternalDocumentation()
//                         .description("API External Documentation")
//                         .url("https://api.external.documentation.url"));
//     }
// }
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "My API", version = "1.0"),
    security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {
}
