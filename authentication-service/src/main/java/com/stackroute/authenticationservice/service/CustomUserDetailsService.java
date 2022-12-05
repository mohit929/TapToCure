package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Inside the loadUserByUsername() of CustomUserDetailsService class");
        User user=userRepository.findbyUserEmail(email);
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


}
