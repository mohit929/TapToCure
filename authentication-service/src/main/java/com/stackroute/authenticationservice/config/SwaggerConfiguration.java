package com.stackroute.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfiguration {
    public String Authorization_Header="Authorization";

    @Bean
    public Docket getDocket()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKey()))
                .select().apis(RequestHandlerSelectors.basePackage("com.stackroute")).paths(PathSelectors.any()).build();
    }

    public ApiKey apiKey()
    {
        return new ApiKey("JWT", Authorization_Header,"Header");
    }

    public List<SecurityContext> securityContexts()
    {
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReference()).build());
    }

    public List<SecurityReference> securityReference()
    {  AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {authorizationScope}));
    }



    private ApiInfo getInfo() {
        return new ApiInfo("Authentication-Service","Kindly generate token and access endpoint's.","1.0","","Mohit Suryawanshi","","");
    }

}
