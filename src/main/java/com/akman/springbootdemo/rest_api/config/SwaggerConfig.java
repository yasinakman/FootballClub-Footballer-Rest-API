package com.akman.springbootdemo.rest_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.any;

/**
 * This is for Swagger UI configs
 * http://localhost:8090/swagger-ui.html for see swagger-ui
 *
 * @author yasina
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.app-info.name}")
    private String name;

    @Value("${swagger.app-info.description}")
    private String description;

    @Value("${swagger.app-info.term-of-service-url}")
    private String termOfServiceUrl;

    @Value("${swagger.app-info.version}")
    private String version;

    @Value("${swagger.app-info.license}")
    private String license;

    @Value("${swagger.app-info.license-url}")
    private String licenseUrl;

    @Value("${swagger.app-info.contact.name}")
    private String contactName;

    @Value("${swagger.app-info.contact.url}")
    private String contactURL;

    @Value("${swagger.app-info.contact.email}")
    private String contactEmail;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("name")
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.akman.springbootdemo.rest_api.controller"))
                .paths(any())
                .build()
                .apiInfo(apiInfo());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder
                .builder()
                .deepLinking(true)
                .docExpansion(DocExpansion.NONE)
                .showExtensions(true)
                .defaultModelExpandDepth(1)
                .defaultModelsExpandDepth(1)
                .operationsSorter(OperationsSorter.ALPHA)
                .build();
    }

    private ApiInfo apiInfo() {
        final Contact contact = new Contact(contactName, contactURL, contactEmail);
        return new ApiInfo(name, description, version, termOfServiceUrl, contact, license, licenseUrl, Collections.emptyList());
    }
}