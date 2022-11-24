package com.stackroute.authenticationservice;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationServiceApplication implements CommandLineRunner {
   @Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user=new User();
//		user.setUsername("Mohit Suryawanshi");
//		user.setEmail("mohit929surya1c@gmail.com");
//		user.setPassword("7477008050");
//		user.setRole("doctor");
//		userRepository.save(user);
//


	}
}
