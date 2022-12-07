package com.stackroute.appointmentservice.rabbitconsumer;

import com.stackroute.appointmentservice.dto.ClinicDto;
import com.stackroute.appointmentservice.dto.PatientDto;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.InvalidDateTimeException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Clinic;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.rabbitconfiguration.MessageConfiguration;
import com.stackroute.appointmentservice.service.AppointmentService;
import com.stackroute.appointmentservice.service.PatientService;
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
    @Autowired
    PatientService patientService;

    @RabbitListener(queues = MessageConfiguration.C_QUEUE)
    public void clinicConsumer(ClinicDto clinicDto) throws ParseException, InvalidDateTimeException, AppointmentAlreadyExistsException, CloneNotSupportedException {
        for (Appointment appointment : clinicDto.getAppointments()) {
            appointmentService.createAppointment(appointment);
        }
        logger.info("Consumed: Clinic");
    }

    @RabbitListener(queues = MessageConfiguration.PATIENT_QUEUE_OF_REGISTRATION_SERVICE)
    public void patientConsumer(PatientDto patientDto) {
        Patient patient = new Patient(Integer.parseInt(patientDto.getUserId()),"",patientDto.getEmailId(),patientDto.getPhoneNo());
        patientService.addPatient(patient);
        logger.info("Consumed: Patient DTO " + patientDto);
    }
}
