package com.stackroute.authenticationservice.messagequeue;

import com.stackroute.authenticationservice.config.MessageQueueConfiguration;
import com.stackroute.authenticationservice.dto.UserDTO;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueListener {

    @Autowired
    CustomUserDetailsService customUserDetailsService;



    @RabbitListener(queues =MessageQueueConfiguration.AUTH_QUEUE)
    public void receiveFromRegistrationService(UserDTO userDTO) {
        log.info("Message Received from Registration-Service: "+userDTO);
        if(userDTO!=null)
        {
            User user=customUserDetailsService.findbyUserEmail(userDTO.getEmailId());
            if(user!=null)
            {
               user.setPassword(userDTO.getPassword());
               customUserDetailsService.save(user);
               log.info("Changed Password of old registered user");
            }
            else{
                User user1=new User();
                user1.setUsername(userDTO.getUserName());
                user1.setEmail(userDTO.getEmailId());
                user1.setRole(String.valueOf(userDTO.getRole()));
                user1.setPassword(userDTO.getPassword());
                customUserDetailsService.save(user1);
                log.info("Saved newly registered user received from Registration-Service");
            }
        }
        else {
            log.info("There is no incoming data from Registration-Service");
        }


}}
