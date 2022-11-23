package com.stackroute.appointmentservice.service;


import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
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

    Patient existingPatient;
    Appointment existingAppointment;

    AppointmentServiceImpl() {
        logger = Logger.getLogger(AppointmentServiceImpl.class.getSimpleName());
    }

    // this method will create an appointment record in db
    // and returns the created object of appointment if created, else it returns null
    @Override
    public Appointment bookAppointment(Appointment appointment) {
        // Existing Patient: ID must be provided, rest all details are optional, details will be taken from database by ID
        if (patientRapo.existsById(appointment.getPatientDetails().getPatientId())) {
            logger.info("Existing Patient");
            existingPatient = patientRapo.getOne(appointment.getPatientDetails().getPatientId());
        } else // New Patient: All details must be provided, ID is optional
        {
            logger.info("New Patient");

            // saving patient's record in db
            // NOTE: store it before appointment otherwise it will throw exception
            patientRapo.save(appointment.getPatientDetails());
            logger.info("Inserted: Patient's record");
        }

        // saving appointment record in db
        appointment.setAppointmentStatus(AppointmentStatus.Booked);
        appointmentRepo.save(appointment);
        logger.info("Inserted: Appointment record");

        // setting patient's detail in new empty appointment record
        if (existingPatient != null) {
            try {
                appointment.setPatientDetails((Patient) existingPatient.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        existingPatient = null;

        // returning inserted appointment record
        return appointment;
    }


    // this method will update an already existing appointment record in db based on particular appointmentId
    // and returns the updated object of appointment if updated, else it returns null
    public Appointment updateAppointment(Appointment appointment) {
        existingAppointment = appointmentRepo.getOne(appointment.getAppointmentId());

        if (existingAppointment == null) {
            logger.info("Not Received: any existing Appointment record");
            return null;
        } else {
            logger.info("Received: existing Appointment record");

            existingAppointment.setAppointmentStatus(AppointmentStatus.Booked);
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setAppointmentTime(appointment.getAppointmentTime());

            if (existingAppointment.getPatientDetails().getPatientId() == appointment.getPatientDetails().getPatientId()) {
                logger.info("Same Existing Patient");
                // getting patient data from db
                existingPatient = patientRapo.getOne(appointment.getPatientDetails().getPatientId());

                // updating local patient object using db data
                try {
                    appointment.setPatientDetails((Patient) existingPatient.clone());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (patientRapo.existsById(appointment.getPatientDetails().getPatientId())) {
                    logger.info("Different Existing Patient");
                    // getting patient data from db
                    existingPatient = patientRapo.getOne(appointment.getPatientDetails().getPatientId());

                    // copy the patient's info: from new to existing
                    try {
                        existingAppointment.setPatientDetails((Patient) appointment.getPatientDetails().clone());

                        // updating local patient object using db data
                        appointment.setPatientDetails((Patient) existingPatient.clone());

                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.info("Different New Patient");

                    // saving patient's record in db
                    // NOTE: store it before appointment otherwise it will throw exception
                    patientRapo.save(appointment.getPatientDetails());

                    // copy the patient's info: from new to existing
                    try {
                        existingAppointment.setPatientDetails((Patient) appointment.getPatientDetails().clone());
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("Updated: Patient's record in appointment");
                }
            }

            // updating existingAppointment record in db
            appointmentRepo.save(existingAppointment);
            logger.info("Updated: existingAppointment record");

            existingAppointment = null;
            existingPatient = null;

            return appointment;
        }
    }

    // this method will delete an existing appointment record in db based on particular appointmentId
    // and returns the deleted object of appointment if deleted, else it returns null
    @Override
    public Appointment deleteAppointment(int appointmentId) {

        // Storing the data of appointment record before deleting it from db
        Appointment appointment = getAppointment(appointmentId);

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
    public Appointment getAppointment(int appointmentId)
    {
        existingAppointment = appointmentRepo.findById(appointmentId).get();

        if(existingAppointment!=null)
            return existingAppointment;
        else
            return null;
    }

    // this method will get all existing appointment record from db
    // and returns the list of appointment if found, else it returns null
    public List<Appointment> getAppointment() {
        return appointmentRepo.findAll();
    }
}
