package com.stackrout.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatServiceWithoutWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServiceWithoutWebsocketApplication.class, args);
		System.out.println("server started");
	}

}
