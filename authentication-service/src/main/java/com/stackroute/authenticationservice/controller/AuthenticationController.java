package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.model.AuthenticationRequest;
import com.stackroute.authenticationservice.model.AuthenticationResponse;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.sonatype.aether.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping(value = "/generateToken")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
    {   String token=null;
        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        token=jwtUtil.generateToken(userDetails,authenticationRequest);
        }
        catch(BadCredentialsException exception)
        {
            throw new InvalidCredentialsException("Credentials are not correct.Kindly try with another credentials");
        }
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken(token);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAuthority('doctor')")
    public  String doctor()
    {
        return "hi doctor";
    }
    @GetMapping("/patient")
    @PreAuthorize("hasAuthority('patient')")
    public String patient()
    {
        return "Hi patient";
    }

    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome to Authentication-Service";
    }
}
