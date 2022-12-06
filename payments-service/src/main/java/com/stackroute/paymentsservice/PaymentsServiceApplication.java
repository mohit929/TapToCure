package com.stackroute.paymentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class PaymentsServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PaymentsServiceApplication.class, args);

		System.out.println("startingapplication@@@");
		
		
	}
	
	public Docket apis() {
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.stackroute.paymentsservice.controller")).build();
		
	}
	

}
