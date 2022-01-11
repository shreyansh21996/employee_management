package com.mppkvvcl.consumerprofile.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                /*.apis(RequestHandlerSelectors.any())*/
                .apis(RequestHandlerSelectors.basePackage("com.mppkvvcl.consumerprofile.controller"))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .pathMapping("/")
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder()
                .title("consumer profile Api Document")
                .description("CONSUMER PROFILE API Description")
                .termsOfServiceUrl("User Url")
                .license("Devloped By Yatindra")
                .licenseUrl("https://www.linkedin.com/in/yatindrasoni/")
                .contact(new Contact("yatindra", "https://www.linkedin.com/in/yatindrasoni/", "yatindra.soni@gmail.com"))
                .version("1.0")
                .build();
    }
}
