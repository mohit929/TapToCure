package com.stackroute.authenticationservice;

import com.stackroute.authenticationservice.model.Role;
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
	CustomUserDetailsService customUserDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
		System.out.println("###Authentication-Service Started###");
	}

	/**
	 *
	 * Adding default Admin below.
	 */
	@Override
	public void run(String... args) throws Exception {
		User user=new User();
		user.setUsername("Deepak Suryawanshi");
		user.setEmail("deepak.suryawanshi@gmail.com");
		user.setRole(String.valueOf(Role.Admin));
		user.setPassword("Deepak@123");
		if(customUserDetailsService.findbyUserEmail(user.getEmail())==null)
		{
			customUserDetailsService.save(user);
		}
	}
}
