package com.stackroute.appointmentservice.rabbitconsumer;

import com.stackroute.appointmentservice.dto.ClinicDto;
import com.stackroute.appointmentservice.dto.PatientDto;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.rabbitconfiguration.MessageConfiguration;
import com.stackroute.appointmentservice.service.AppointmentService;
import com.stackroute.appointmentservice.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    Logger logger = Logger.getLogger(Consumer.class.getSimpleName());
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientService patientService;

    /*
    consumer method for clinic object published by clinic-service
    it will get the information of appointments and create new empty appointment slots
     */
    //@RabbitListener(queues = MessageConfiguration.CLINIC_QUEUE_OF_CLINIC_SERVICE)
    public void clinicConsumer(ClinicDto clinicDto) {

        for (Appointment appointment : clinicDto.getAppointments())
        {
            try
            {
                //appointment.setClinicId(clinicDto.getClinicId());
                appointmentService.createAppointment(appointment);
                logger.info("Consumed: Appointment from Clinic DTO");
            }
            catch (Exception e)
            {
                logger.error("Consumption Failed: "+e.getMessage(), e);
            }
        }
    }

    /*
    consumer method for patient object published by registration-service
    it will get the information of newly registered patients and create the copy of those patient
     */
    //@RabbitListener(queues = MessageConfiguration.PATIENT_QUEUE_OF_PATIENT_SERVICE)
    public void patientConsumer(PatientDto patientDto) {
        try {
            Patient patient = new Patient();
            patient.setPatientId(Integer.parseInt(patientDto.getPatientId()));
            patient.setPatientName(patientDto.getPatientName());
            patient.setPatientGender(patientDto.getPatientGender());
            patient.setPatientBloodGroup(patientDto.getPatientBloodGroup());
            patient.setPatientDob(patientDto.getPatientDob());
            patient.setPatientPhoneNumber(patientDto.getPatientPhoneNumber());
            patient.setPatientEmail(patient.getPatientEmail());
            patient.setCity(patientDto.getCity());
            patient.setState(patientDto.getState());
            patient.setPinCode(patientDto.getPinCode());
            patient.setPatientSymptoms(patientDto.getPatientSymptoms());

            patientService.addPatient(patient);
            logger.info("Consumed: Patient DTO");
        } catch (Exception e) {
            logger.error("Consumption Failed: "+e.getMessage(), e);
        }
    }
}
