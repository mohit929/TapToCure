package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.dto.UserDTO;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.model.AuthenticationRequest;
import com.stackroute.authenticationservice.model.AuthenticationResponse;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.sonatype.aether.repository.Authentication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping(value = "/generateToken")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
    {   AuthenticationResponse authenticationResponse=null;
        try{
            authenticationResponse=customUserDetailsService.generateToken(authenticationRequest);
        }
        catch(BadCredentialsException exception)
        {
            throw new InvalidCredentialsException("Credentials are not correct.Kindly try with another credentials");
        }
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAnyAuthority('Doctor','Admin')")
    public  ResponseEntity<?> doctor()
    {
        return ResponseEntity.ok("Welcome Doctor! "+ "\n"+"Disclaimer:If you are doctor or admin then only you can access this endpoint.");
    }
    @GetMapping("/patient")
    @PreAuthorize("hasAnyAuthority('Patient','Admin')")
    public ResponseEntity<?> patient()
    {
        return ResponseEntity.ok("Welcome Patient! "+ "\n"+"Disclaimer:If you are patient or admin then only you can access this endpoint.");
    }

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome()
    {
        return ResponseEntity.ok("Welcome to Authentication-Service."+"\n"+"Disclaimer:This endpoint is accessible to all,regardless"
         +" of any restriction based on role");
    }





}
