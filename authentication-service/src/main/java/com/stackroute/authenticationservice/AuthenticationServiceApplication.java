package com.stackroute.authenticationservice;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
@EnableEurekaClient
public class AuthenticationServiceApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user=userRepository.findbyUserEmail("mohit929surya1c@gmail.com");
//		System.out.println(user);
//		UserDetails userDetails=customUserDetailsService.loadUserByUsername("mohit929surya1c@gmail.com");
//		System.out.println(userDetails);
	}
}
