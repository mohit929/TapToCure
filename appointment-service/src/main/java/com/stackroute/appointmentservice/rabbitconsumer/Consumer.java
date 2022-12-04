package com.stackroute.appointmentservice.rabbitconsumer;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.InvalidDateTimeException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Clinic;
import com.stackroute.appointmentservice.rabbitconfiguration.MessageConfiguration;
import com.stackroute.appointmentservice.service.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class Consumer {
    Logger logger = Logger.getLogger(Consumer.class.getSimpleName());

    @Autowired
    AppointmentService appointmentService;

    @RabbitListener(queues = MessageConfiguration.C_QUEUE)
    public void clinicConsumer(Clinic clinic) throws ParseException, InvalidDateTimeException, AppointmentAlreadyExistsException, CloneNotSupportedException {
        for(Appointment appointment: clinic.getAppointments())
        {
            appointmentService.createAppointment(appointment);
        }
        logger.info("Consumed: Clinic");
    }
}
