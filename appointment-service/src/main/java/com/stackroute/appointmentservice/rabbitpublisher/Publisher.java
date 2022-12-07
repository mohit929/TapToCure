package com.stackroute.appointmentservice.rabbitpublisher;


import com.stackroute.appointmentservice.dto.AppointmentDto;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Clinic;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.rabbitconfiguration.MessageConfiguration;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    Logger logger = Logger.getLogger(Publisher.class.getSimpleName());

    @Autowired
    private RabbitTemplate template;

    @Autowired
    AppointmentDto appointmentDto;


    public void sendAppointment(Appointment appointment, String status) {

        appointmentDto.setAppointmentId(appointment.getAppointmentId());
        appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDto.setAppointmentStatus(appointment.getAppointmentStatus());
        appointmentDto.setPatientDetails(appointment.getPatientDetails());

        template.convertAndSend(MessageConfiguration.EXCHANGE, MessageConfiguration.A_KEY, appointmentDto);
        logger.info("Published: " + status + ", Appointment record");
    }

    // Temporary code: For RabbitMQ Publisher Testing
    public void sendPatient(Patient patient) {
        template.convertAndSend(MessageConfiguration.EXCHANGE, MessageConfiguration.P_KEY, patient);
        logger.info("Published: Patient record");
    }

    // Temporary code: For RabbitMQ Publisher Testing
    public void sendClinic(Clinic clinic) {
        template.convertAndSend(MessageConfiguration.EXCHANGE, MessageConfiguration.C_KEY, clinic);
        logger.info("Published: Clinic record");
    }
}
