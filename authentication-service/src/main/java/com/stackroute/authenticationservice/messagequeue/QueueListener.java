package com.stackroute.authenticationservice.messagequeue;

import com.stackroute.authenticationservice.dto.UserDTO;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @Autowired
    CustomUserDetailsService customUserDetailsService;



    @RabbitListener(queues = "AUTH_Q")
    public void receiveFromRegistrationService(UserDTO userDTO) {
        System.out.println(userDTO);
        if(userDTO!=null)
        {
            User user=customUserDetailsService.findbyUserEmail(userDTO.getEmail());
            if(user!=null)
            {
               user.setPassword(userDTO.getPassword());
               customUserDetailsService.save(user);
            }
            else{
                User user1=new User();
                user1.setUsername(userDTO.getUsername());
                user1.setEmail(userDTO.getEmail());
                user1.setRole(String.valueOf(userDTO.getRole()));
                user1.setPassword(userDTO.getPassword());
                customUserDetailsService.save(user1);
            }
        }


}}
