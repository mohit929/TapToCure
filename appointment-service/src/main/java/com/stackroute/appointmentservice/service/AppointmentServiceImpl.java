package com.stackroute.appointmentservice.service;


import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.repo.AppointmentRepo;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.apache.log4j.BasicConfigurator;
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

    AppointmentServiceImpl()
    {
        logger = Logger.getLogger(AppointmentServiceImpl.class.getSimpleName());
        BasicConfigurator.configure();
    }

    // this method will create an appointment record in db
    // and returns the created object of appointment if created, else it returns null
    @Override
    public Appointment bookAppointment(Appointment appointment)
    {
        // checking appointment record should not exist in db
        if (!appointmentRepo.existsById(appointment.getAppointmentId()))
        {
            // saving patient's record in db
            // NOTE: store it before appointment otherwise it will throw exception
            patientRapo.save(appointment.getPatientDetails());
            logger.info("Inserted: Patient's record");

            // saving appointment record in db
            appointmentRepo.save(appointment);
            logger.info("Inserted: Appointment record");

            // returning inserted appointment record
            return appointment;
        }
        return null;
    }

    // this method will update an already existing appointment record in db based on particular appointmentId
    // and returns the updated object of appointment if updated, else it returns null
    public Appointment updateAppointment(Appointment appointment)
    {
        // checking appointment record should exist in db
        if (appointmentRepo.existsById(appointment.getAppointmentId()))
        {
            // saving/updating patient's record in db
            // NOTE: store it before appointment otherwise it will throw exception
            patientRapo.save(appointment.getPatientDetails());
            logger.info("Inserted: Patient's record");

            // saving/updating appointment record in db
            appointmentRepo.save(appointment);
            logger.info("Inserted: Appointment record");

            // returning updated appointment record
            return appointment;
        }

        return null;
    }

    // this method will delete an existing appointment record in db based on particular appointmentId
    // and returns the deleted object of appointment if deleted, else it returns null
    @Override
    public Optional<Appointment> deleteAppointment(int appointmentId)
    {
        // Storing the data of appointment record before deleting it from db
        Optional<Appointment> appointment = getAppointment(appointmentId);

        if (appointment != null)
        {
            // deleting appointment record from db
            appointmentRepo.deleteById(appointmentId);
            logger.info("Deleted: Appointment record");
        }

        // returning stored appointment object that was deleted from db
        return appointment;
    }

    // this method will search an existing appointment record in db based on particular appointmentId
    // and returns the object of appointment if found, else it returns null
    public Optional<Appointment> getAppointment(int appointmentId)
    {
        return appointmentRepo.findById(appointmentId);
    }

    // this method will get all existing appointment record from db
    // and returns the list of appointment if found, else it returns null
    public List<Appointment> getAppointment()
    {
        return appointmentRepo.findAll();
    }
}
