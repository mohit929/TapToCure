package com.stackroute.registrationservice.publisher;

import com.stackroute.registrationservice.config.Config;
import com.stackroute.registrationservice.entity.User;
import com.stackroute.registrationservice.rabbitmqResponseDTO.EmailDTO;
import com.stackroute.registrationservice.rabbitmqResponseDTO.OtpDto;
import com.stackroute.registrationservice.rabbitmqResponseDTO.PatientDoctorDTO;
import com.stackroute.registrationservice.rabbitmqResponseDTO.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        String role= String.valueOf(user.getRole());
        switch (role){
            case "Patient":
                PatientDoctorDTO p=new PatientDoctorDTO(user.getUserId(),user.getEmailId(),user.getMobileNo());
                rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.PKEY,p);
                System.out.println("Patient Queue");
                break;
            case "Doctor":
                PatientDoctorDTO d=new PatientDoctorDTO(user.getUserId(),user.getEmailId(),user.getMobileNo());
                rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.DKEY,d);
                System.out.println("Doctor Queue");
                break;

        }

    }

    public void sendToEmailQueue(User user) {

        EmailDTO e= new EmailDTO(user.getEmailId(),user.getUserName());
        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.EKEY,e);

    }

    public void sendToAuthentication(User user) {
        UserDTO userDTO=new UserDTO(user.getEmailId(),user.getUserName(),user.getPassword(),user.getRole());
        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.AUTHKEY,userDTO);
        System.out.println("Patient Queue");
    }

    public void sendOtp(String emailId, int otpno) {

        //OTP-Q queue
        OtpDto e= new OtpDto(emailId,otpno);
        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.OTPKEY,e);

    }

}
