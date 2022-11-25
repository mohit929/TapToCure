package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyBookedException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyCancelledException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.AppointmentNotExistsException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.AppointmentRepo;
import org.apache.log4j.Logger;
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
    this method will create new appointment record in db
        having specific id, date and time
        and empty patient
    it will return the object of appointment
     */
    public Appointment createAppointment(Appointment appointment) throws AppointmentAlreadyExistsException {
        // creating dummy patient for new available appointment
        Patient emptyPatient = new Patient(1, "");
        patientService.addPatient(emptyPatient);
        logger.info("Added: Empty patient record");

        // storing patient object in appointment object
        try {
            appointment.setPatientDetails((Patient) emptyPatient.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // saving appointment record in db
        appointment.setAppointmentStatus(AppointmentStatus.AVAILABLE);
        if (!appointmentRepo.existsById(appointment.getAppointmentId())) {
            appointmentRepo.save(appointment);
        } else {
            throw new AppointmentAlreadyExistsException("An appointment was already created with this id: " + appointment.getAppointmentId());
        }
        logger.info("Created: New appointment record");

        // returning inserted appointment record
        return appointment;
    }

    /*
    It will add a patient in existing appointment
        it checks for patient existence in db
        if patient is new then only it inserts the patient details in db
        else takes the patient info from db itself
        then update the appointment detail in db along with patient info
     */
    @Override
    public Appointment bookAppointment(Appointment modifiedAppointment) throws AppointmentNotExistsException, AppointmentAlreadyBookedException {
        existingAppointment = null;
        existingPatient = null;
        // searching for existing appointment in db
        existingAppointment = getAppointment(modifiedAppointment.getAppointmentId());
        // checking for appointment's availability
        if (existingAppointment.getAppointmentStatus().equals(AppointmentStatus.BOOKED)) {
            throw new AppointmentAlreadyBookedException("The appointment id: " + existingAppointment.getAppointmentId() + " is already booked by: " + existingAppointment.getPatientDetails().getPatientName());
        } else {
            // updating the patient in existing appointment by modified patient
            if (patientService.isPatientExists(modifiedAppointment.getPatientDetails())) {
                logger.info("Existing Patient");
                existingPatient = patientService.getPatient(modifiedAppointment.getPatientDetails().getPatientId());
                try {
                    existingAppointment.setPatientDetails((Patient) existingPatient.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            } else {
                logger.info("New Patient");
                patientService.addPatient(modifiedAppointment.getPatientDetails());
                logger.info("Inserted: Patient's record");
                try {   // updating the patient in existing appointment by modified patient
                    existingAppointment.setPatientDetails((Patient) modifiedAppointment.getPatientDetails().clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

            // saving appointment record in db
            existingAppointment.setAppointmentStatus(AppointmentStatus.BOOKED);
            //existingAppointment.setAppointmentTime(modifiedAppointment.getAppointmentTime());
            //existingAppointment.setAppointmentDate(modifiedAppointment.getAppointmentDate());
            appointmentRepo.save(existingAppointment);
            logger.info("Booked: Appointment record");
        }

        // returning inserted appointment record
        return existingAppointment;
    }

    /*
   this method will search for an existing appointment in db with specified appointment id
   if found it saves the existing appointment record from db
            it updates the fields of existing appointment and save it on db
    */
    public Appointment updateAppointment(Appointment modifiedAppointment) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException {
        existingAppointment = null;
        existingPatient = null;
        // search for existing appointment in db
        existingAppointment = getAppointment(modifiedAppointment.getAppointmentId());
        // Do update
        logger.info("Received: existing Appointment record, Starting update");

        // checking for cancellation request
        if (modifiedAppointment.getAppointmentStatus() == AppointmentStatus.CANCELLED) {
            logger.info("Sending cancellation request from update module");
            return cancelAppointment(modifiedAppointment.getAppointmentId());
        }

        // to update the appointmet its status should not be "AVAILABLE"
        if (existingAppointment.getAppointmentStatus() == AppointmentStatus.AVAILABLE) {
            throw new AppointmentNotExistsException("No appointment is booked for this Id: " + existingAppointment.getAppointmentId());
        }
        // updating fields of existing appointment by the fields of modified appointment
        existingAppointment.setAppointmentStatus(AppointmentStatus.BOOKED);
        if (modifiedAppointment.getAppointmentDate() != null)
            existingAppointment.setAppointmentDate(modifiedAppointment.getAppointmentDate());
        if (modifiedAppointment.getAppointmentTime() != null)
            existingAppointment.setAppointmentTime(modifiedAppointment.getAppointmentTime());

        // To check the change in patient detail
        Patient originalPatient = existingAppointment.getPatientDetails();
        Patient modifiedPatient = modifiedAppointment.getPatientDetails();

        // logic to update the patient details in existing appointment
        if (patientService.isPatientExists(modifiedPatient)) {   // existing patient
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
        } else {   // new patient
            logger.info("New Patient");
            patientService.addPatient(modifiedPatient);

            try {   // updating the patient in existing appointment by modified patient
                existingAppointment.setPatientDetails((Patient) modifiedPatient.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            logger.info("Updated: Patient's record in appointment");
        }

        // updating appointment record in db
        appointmentRepo.save(existingAppointment);
        logger.info("Updated: existingAppointment record");

        return existingAppointment;
    }

    public Appointment cancelAppointment(int appointmentId) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException {
        existingAppointment = null;
        // Storing the data of appointment record before deleting it from db
        existingAppointment = getAppointment(appointmentId);
        // checking for cancellation request
        if (existingAppointment.getAppointmentStatus() == AppointmentStatus.CANCELLED) {
            throw new AppointmentAlreadyCancelledException("Appoitnment was already cancelled: " + appointmentId);
        } else if (existingAppointment.getAppointmentStatus() == AppointmentStatus.AVAILABLE) {
            throw new AppointmentNotExistsException("Trying to cancel AVAILABLE slot: " + appointmentId);
        }
        existingAppointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        // deleting the existing appointment
        appointmentRepo.save(existingAppointment);
        logger.info("Cancelled: Appointment");
        // returning stored appointment object that was deleted from db
        return existingAppointment;
    }

    /*
    first this method will search for an existing appointment in db based on specified appointment id
    if found it will make a local copy of that appointment and delete it from db
             it will return the deleted appointment object
    else throw AppointmentNotExistsException exception
     */
    @Override
    public Appointment deleteAppointment(int appointmentId) throws AppointmentNotExistsException {
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
   this method will get all the appointment records from the db
   if found it will return the list of appointment
   else throw AppointmentNotExistsException exception
    */
    public List<Appointment> getAppointment() throws AppointmentNotExistsException {
        List<Appointment> appointmentList = appointmentRepo.findAll();
        if (!appointmentList.isEmpty()) {
            return appointmentList;
        }
        throw new AppointmentNotExistsException("No appointment is booked till now");
    }

    /*
    this method will find an existing appointment in db based on specified appointment id
    if found it will return the object of appointment
    else throw AppointmentNotExistsException exception
     */
    public Appointment getAppointment(int appointmentId) throws AppointmentNotExistsException {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        }
        throw new AppointmentNotExistsException("No appointment exists for appointmentId: " + appointmentId);
    }

}
