package com.stackroute.appointmentservice.rabbitpublisher;


import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Clinic;
import com.stackroute.appointmentservice.rabbitconfiguration.MessageConfiguration;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Publisher {
    Logger logger = Logger.getLogger(Publisher.class.getSimpleName());

    @Autowired
    private RabbitTemplate template ;

    public void sendClinic(Clinic clinic)
    {
        template.convertAndSend(MessageConfiguration.EXCHANGE,MessageConfiguration.C_KEY,clinic);
        logger.info("Published: Clinic record");
    }

    public void sendAppointment(Appointment appointment, String status)
    {
        template.convertAndSend(MessageConfiguration.EXCHANGE,MessageConfiguration.A_KEY,appointment);
        logger.info("Published: "+status+", Appointment record");
    }
}
