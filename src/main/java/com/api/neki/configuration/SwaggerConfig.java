package com.api.neki.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
//    @Bean
//    public OpenAPI custmOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
//                        )
//                )
//                .info(new Info()
//                        .title("Defesa Civil API")
//                        .version("1.0v")
//                        .description("")
//                        .contact(new Contact()
//                                .name("Defesa Civil")
//                                .email("defesacivil@petropolis.rj.gov.br")
//                        )
//                )
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
//    }
}


