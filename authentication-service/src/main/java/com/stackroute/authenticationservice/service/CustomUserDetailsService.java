package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.model.AuthenticationRequest;
import com.stackroute.authenticationservice.model.AuthenticationResponse;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Inside the loadUserByUsername() of CustomUserDetailsService class");
        User user=findbyUserEmail(email);
        CustomUserDetails userDetails=new CustomUserDetails(user);
        if(user!=null)
        {
            return userDetails;
        }
        else {
            log.info("Inside the else part in CustomUserDetailsService when user is not found in MYSQL-database");
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public User findbyUserEmail(String email)
    {
        return userRepository.findbyUserEmail(email);
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public AuthenticationResponse generateToken(AuthenticationRequest authenticationRequest) throws InvalidCredentialsException
    {   String token=null;
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
            UserDetails userDetails=loadUserByUsername(authenticationRequest.getEmail());
            token=jwtUtil.generateToken(userDetails,authenticationRequest);
        }
        catch(BadCredentialsException exception)
        {
            throw new InvalidCredentialsException("Credentials are not correct.Kindly try with another credentials");
        }
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken(token);
        authenticationResponse.setRole(jwtUtil.extractRole(token));
        return authenticationResponse;
    }


}
