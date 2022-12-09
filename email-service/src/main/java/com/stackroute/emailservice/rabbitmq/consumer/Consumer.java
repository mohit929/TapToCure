package com.stackroute.emailservice.rabbitmq.consumer;


import com.stackroute.emailservice.rabbitmq.configuration.MessageConfiguration;
import com.stackroute.emailservice.rabbitmq.dto.Appointment;
import com.stackroute.emailservice.rabbitmq.dto.EmailDTO;
import com.stackroute.emailservice.rabbitmq.dto.OtpDto;
import com.stackroute.emailservice.service.EmailService;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class Consumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = MessageConfiguration.A_QUEUE)
    public void patientConsumer(Appointment appointment) {
        emailService.appintmentDto(appointment);

        System.out.println("Consumed: Appointment "+appointment);
    }
    @RabbitListener(queues = MessageConfiguration.E_QUEUE)
    public void emailDTO (EmailDTO emailDTO) {
        emailService.consumeEmailDto(emailDTO);
        System.out.println("Consumed: Email "+emailDTO);
    }
    @RabbitListener(queues = MessageConfiguration.O_QUEUE)
    public void otpDTO (OtpDto otpDto) {
        emailService.consumeOtpDto(otpDto);
        System.out.println("Consumed: otp "+otpDto);
    }


}