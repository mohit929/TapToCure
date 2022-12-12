package com.stackrout.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChatServiceWithoutWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServiceWithoutWebsocketApplication.class, args);
		System.out.println("server started");
	}

}
