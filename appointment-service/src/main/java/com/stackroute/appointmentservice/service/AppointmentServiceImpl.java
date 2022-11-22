package com.stackroute.appointmentservice.service;


import com.stackroute.appointmentservice.model.*;
import com.stackroute.appointmentservice.repo.AppointmentRepo;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    Logger logger;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    PatientRepo patientRapo;

    @Autowired
    Patient existingPatient;
    @Autowired
    Appointment existingAppointment;

    AppointmentServiceImpl() {
        logger = Logger.getLogger(AppointmentServiceImpl.class.getSimpleName());
        //BasicConfigurator.configure();
    }

    // this method will create an appointment record in db
    // and returns the created object of appointment if created, else it returns null
    @Override
    public Appointment bookAppointment(Appointment appointment)
    {
        // if patient id is not provided
        if(appointment.getPatientDetails().getPatientId()==0)
        {
            // saving patient's record in db
            // NOTE: store it before appointment otherwise it will throw exception
            patientRapo.save(appointment.getPatientDetails());
            logger.info("Inserted: Patient's record");
        }
        else
        {
            existingPatient = patientRapo.getOne(appointment.getPatientDetails().getPatientId());
            logger.info("This Patient's record already exists");
        }

        // saving appointment record in db
        appointment.setAppointmentStatus(AppointmentStatus.Booked);
        appointmentRepo.save(appointment);
        logger.info("Inserted: Appointment record");

        // setting patient's detail in new empty appointment record
        // NOTE: In future we need to change this if we have so many fields in Patient
        //appointment.setPatientDetails(existingPatient);
        appointment.getPatientDetails().setPatientName(existingPatient.getPatientName());

        // returning inserted appointment record
        return appointment;
    }



    // this method will update an already existing appointment record in db based on particular appointmentId
    // and returns the updated object of appointment if updated, else it returns null
    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = appointmentRepo.getOne(appointment.getAppointmentId());

        // checking appointment record should exist in db
        if (existingAppointment != null) {
            logger.info("Received: existing Appointment record");

            //existingAppointment.setAppointmentStatus(AppointmentStatus.Booked);
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setAppointmentTime(appointment.getAppointmentTime());

            // same patient is going to use this appointment
            if (appointment.getPatientDetails() == null)
            {
                Patient existingPatient = patientRapo.getOne(existingAppointment.getPatientDetails().getPatientId());
                // setting patient's detail in new appointment record
                appointment.setPatientDetails(existingPatient);
            }
            else
            {
                // updating patient's record in existingAppointment object
                existingAppointment.setPatientDetails(appointment.getPatientDetails());

                // updating patient's record in db
                // NOTE: store it before appointment otherwise it will throw exception
                patientRapo.save(existingAppointment.getPatientDetails());
                logger.info("Updated: Patient's record");
            }

            // updating existingAppointment record in db
            appointmentRepo.save(existingAppointment);
            logger.info("Updated: existingAppointment record");

            // returning updated appointment record
            return appointment;
        }
        else
        {
            logger.info("Not Received: any existing Appointment record");
            return null;
        }
    }

    // this method will delete an existing appointment record in db based on particular appointmentId
    // and returns the deleted object of appointment if deleted, else it returns null
    @Override
    public Optional<Appointment> deleteAppointment(int appointmentId) {
        // Storing the data of appointment record before deleting it from db
        Optional<Appointment> appointment = getAppointment(appointmentId);

        if (appointment != null) {
            // deleting appointment record from db
            appointmentRepo.deleteById(appointmentId);
            logger.info("Deleted: Appointment record");
        }

        // returning stored appointment object that was deleted from db
        return appointment;
    }

    // this method will search an existing appointment record in db based on particular appointmentId
    // and returns the object of appointment if found, else it returns null
    public Optional<Appointment> getAppointment(int appointmentId) {
        return appointmentRepo.findById(appointmentId);
    }

    // this method will get all existing appointment record from db
    // and returns the list of appointment if found, else it returns null
    public List<Appointment> getAppointment() {
        return appointmentRepo.findAll();
    }
}
