package com.stackroute.patientservice.rabbitmqconsumer;

import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.rabbitmqdto.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.stackroute.patientservice.rabbitmqconfig.Config;

@Component
public class RabbitMqConsumer {


    private UserDTO tempUser;
    @RabbitListener(queues = Config.P_QUEUE)
    public void consumeMessageFromQueue(UserDTO userDTO) {
        System.out.println("Message Has recieved from queue with order : "+ userDTO);
        tempUser = userDTO;
    }

    public Patient setPatientDetails(){
        Patient patient = new Patient();
        patient.setPatientId(tempUser.getUserId());
        patient.setPatientEmail(tempUser.getEmailId());
        patient.setPatientPhoneNumber(tempUser.getPhoneNo());
        return patient;
    }
}
