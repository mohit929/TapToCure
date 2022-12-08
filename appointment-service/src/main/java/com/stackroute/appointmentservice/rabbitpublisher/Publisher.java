package com.stackroute.appointmentservice.rabbitpublisher;


import com.stackroute.appointmentservice.dto.AppointmentDto;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.rabbitconfiguration.MessageConfiguration;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    Logger logger = Logger.getLogger(Publisher.class.getSimpleName());
    @Autowired
    AppointmentDto appointmentDto;
    @Autowired
    private RabbitTemplate template;

    /*
    this is a publisher method
    it will publish the appointment details
     */
    public void sendAppointment(Appointment appointment, String status) {

        appointmentDto.setAppointmentId(appointment.getAppointmentId());
        appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDto.setAppointmentStatus(appointment.getAppointmentStatus());
        appointmentDto.setPatientDetails(appointment.getPatientDetails());

        template.convertAndSend(MessageConfiguration.EXCHANGE, MessageConfiguration.A_KEY, appointmentDto);
        logger.info("Published: " + status + ", Appointment record");
    }
}
