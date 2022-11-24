package com.stackroute.appointmentservice.service;


import com.stackroute.appointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.AppointmentRepo;
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
    PatientService patientService;
    Appointment existingAppointment;
    Patient existingPatient;

    AppointmentServiceImpl() {
        logger = Logger.getLogger(AppointmentServiceImpl.class.getSimpleName());
    }

    /*
    this method will get all the appointment records from the db
    if found it will return the list of appointment
    else throw AppointmentNotFoundException exception
     */
    public List<Appointment> getAppointment() throws AppointmentNotFoundException
    {
        List<Appointment> appointmentList = appointmentRepo.findAll();
        if (!appointmentList.isEmpty()) {
            return appointmentList;
        }
        throw new AppointmentNotFoundException("No appointment is booked till now");
    }

    /*
    this method will find an existing appointment in db based on specified appointment id
    if found it will return the object of appointment
    else throw AppointmentNotFoundException exception
     */
    public Appointment getAppointment(int appointmentId) throws AppointmentNotFoundException
    {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        }
        throw new AppointmentNotFoundException("No appointment is booked for appointmentId: " + appointmentId);
    }

    /*
    first this method will search for an existing appointment in db based on specified appointment id
    if found it will make a local copy of that appointment and delete it from db
             it will return the deleted appointment object
    else throw AppointmentNotFoundException exception
     */
    @Override
    public Appointment deleteAppointment(int appointmentId) throws AppointmentNotFoundException
    {
        existingAppointment = null;
        // Storing the data of appointment record before deleting it from db
        existingAppointment = getAppointment(appointmentId);
        // deleting the existing appointment
        appointmentRepo.deleteById(appointmentId);
        logger.info("Deleted: Appointment record");
        // returning stored appointment object that was deleted from db
        return existingAppointment;
    }

    /*
    this method will create new appointment record in db
    it will return the deleted appointment object
    before creating new appointment
        it checks for patient existence in db
        if patient is new then only it inserts the patient details in db
        else takes the patient info from db itself
        then insert appointment detail in db
     */
    @Override
    public Appointment bookAppointment(Appointment appointment)
    {
        existingPatient = null;
        if(patientService.isPatientExists(appointment.getPatientDetails()))
        {
            logger.info("Existing Patient");
            existingPatient = patientService.getPatient(appointment.getPatientDetails().getPatientId());
            try
            {
                appointment.setPatientDetails((Patient) existingPatient.clone());
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            logger.info("New Patient");
            patientService.addPatient(appointment.getPatientDetails());
            logger.info("Inserted: Patient's record");
        }

        // saving appointment record in db
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
        appointmentRepo.save(appointment);
        logger.info("Inserted: Appointment record");

        // returning inserted appointment record
        return appointment;
    }

    /*
    this method will search for an existing appointment in db with specified appointment id
    if found it saves the existing appointment record from db
             it updates the fields of existing appointment and save it on db
     */
    public Appointment updateAppointment(Appointment modifiedAppointment) throws AppointmentNotFoundException {
        existingAppointment = null;
        existingPatient = null;
        // search for existing appointment in db
        existingAppointment = getAppointment(modifiedAppointment.getAppointmentId());

        if (existingAppointment == null) { // Do not update
            logger.info("Not Received: any existing Appointment record, Do not update");
            return null;
        } else { // Do update
            logger.info("Received: existing Appointment record, Starting update");

            // updating fields of existing appointment by the fields of modified appointment
            existingAppointment.setAppointmentStatus(AppointmentStatus.BOOKED);
            existingAppointment.setAppointmentDate(modifiedAppointment.getAppointmentDate());
            existingAppointment.setAppointmentTime(modifiedAppointment.getAppointmentTime());

            // To check the change in patient detail
            Patient originalPatient = existingAppointment.getPatientDetails();
            Patient modifiedPatient = modifiedAppointment.getPatientDetails();

            // logic to update the patient details in existing appointment
            if (patientService.isPatientExists(modifiedPatient))
            {   // existing patient
                if (originalPatient.getPatientId() == modifiedPatient.getPatientId()) {
                    logger.info("Existing Patient: Same");
                    // updating patient object by db object records
                    existingPatient = patientService.getPatient(modifiedPatient.getPatientId());
                } else {
                    logger.info("Existing Patient: Different");
                    // getting patient data from db
                    existingPatient = patientService.getPatient(modifiedPatient.getPatientId());
                }

                try {   // updating the patient in modified appointment by db record of original patient
                    existingAppointment.setPatientDetails((Patient) existingPatient.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            else
            {   // new patient
                logger.info("New Patient");
                patientService.addPatient(modifiedPatient);

                try {   // updating the patient in existing appointment by modified patient
                    existingAppointment.setPatientDetails((Patient) modifiedPatient.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                logger.info("Updated: Patient's record in appointment");
            }

        }

        // updating appointment record in db
        appointmentRepo.save(existingAppointment);
        logger.info("Updated: existingAppointment record");

        return existingAppointment;
    }

}
