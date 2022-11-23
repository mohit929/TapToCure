package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.model.AuthenticationRequest;
import com.stackroute.authenticationservice.model.AuthenticationResponse;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping("/accessDoctor")
    public String  accessDoctor()
    {
        return "Doctor can access this";
    }
    @PreAuthorize("hasAuthority('patient')")
    @GetMapping("/accessPatient")
    public String accessPatient()
    {
        return "Patient can access this";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException exception)
        {
            exception.getMessage();
            throw new Exception("Invalid Username and Password");
        }
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token=jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setTokem(token);
        return ResponseEntity.ok(authenticationResponse);
    }
}
