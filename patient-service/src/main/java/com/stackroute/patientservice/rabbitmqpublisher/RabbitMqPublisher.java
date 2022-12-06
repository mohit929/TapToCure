package com.stackroute.patientservice.rabbitmqpublisher;

import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.rabbitmqconfig.Config;
import com.stackroute.patientservice.rabbitmqdto.PatientDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Patient patient){
        PatientDTO patientDTO = new PatientDTO(patient.getPatientId(),patient.getPatientName(), patient.getPatientGender(),
                patient.getPatientBloodGroup(), patient.getPatientDob(), patient.getPatientPhoneNumber(), patient.getPatientEmail(),
                patient.getCity(), patient.getState(), patient.getPinCode(), patient.getPatientSymptoms());
        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.PKEY, patientDTO);
        System.out.println("Patient Queue");
    }
}
