package com.stackroute.clinicservice.publisher;

import com.stackroute.clinicservice.config.Config;
import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.model.AppointmentStatus;
import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.rabbitmqResponseDTO.ClinicDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;
@Service
public class RabbitmqPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(ClinicDetail clinicDetail){
        ClinicDTO clinicDTO=new ClinicDTO(clinicDetail.getClinicID(),clinicDetail.getClinicName(),clinicDetail.getState(),
                clinicDetail.getCity(), clinicDetail.getArea(), clinicDetail.getBuildingNumber(), clinicDetail.getTotalSlots(), clinicDetail.getOpeningTime(),
                clinicDetail.getClosingTime(),clinicDetail.getServiceDays(), clinicDetail.getDoctorId(), clinicDetail.getDoctorName(), clinicDetail.getSpecialization(),
                clinicDetail.getDoctor_contact(), clinicDetail.getDoctor_mail(), clinicDetail.getAppointment());
        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.Clinic_Key,clinicDTO);
        System.out.println("Clinic Detail Queue");
    }
}
