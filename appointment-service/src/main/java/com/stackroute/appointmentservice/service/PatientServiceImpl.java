package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PatientServiceImpl implements PatientService {
    Logger logger = Logger.getLogger(PatientServiceImpl.class.getSimpleName());
    @Autowired
    PatientRepo patientRapo;
    @Autowired
    Patient patient;

    /*
    this method will create new patient record in db
    only when the patient is new
     */
    @Override
    public Patient addPatient(Patient patient) {
        if (!patientRapo.existsById(patient.getPatientId())) {
            logger.info("Creating: New Patient record");
            return patientRapo.save(patient);
        } else {
            logger.info("Updating: New Patient record");
            checkAndUpdatePatient(patientRapo.getOne(patient.getPatientId()), patient);
        }
        return patient;
    }

    /*
    this method will check the existence of a patient in db
    returns true if found else returns false
     */
    @Override
    public boolean isPatientExists(Patient patient) {
        return patientRapo.existsById(patient.getPatientId());
    }

    /*
    this method will find the patient record in db of specific id
     */
    @Override
    public Patient getPatient(int patientId) {
        return patientRapo.getOne(patientId);
    }

    /*
   this method will compare 2 patients object
   update the details in existing record in db
    returns one with the latest information
    */
    @Override
    public Patient checkAndUpdatePatient(Patient existingPatient, Patient modifiedPatient) {
        boolean isUpdated = false;
        // checking and updating name
        if ((existingPatient.getPatientName() == null && modifiedPatient.getPatientName() != null)
                || (modifiedPatient.getPatientName() != null
                && existingPatient.getPatientName() != null
                && !modifiedPatient.getPatientName().isBlank()
                && !modifiedPatient.getPatientName().equalsIgnoreCase("string")
                && !modifiedPatient.getPatientName().equalsIgnoreCase(existingPatient.getPatientName()))) {
            existingPatient.setPatientName(modifiedPatient.getPatientName());
            isUpdated = true;
        }
        // checking and updating gender
        if ((existingPatient.getPatientGender() == null && modifiedPatient.getPatientGender() != null) || (modifiedPatient.getPatientGender() != null && existingPatient.getPatientGender() != null && !modifiedPatient.getPatientGender().isBlank() && !modifiedPatient.getPatientGender().equalsIgnoreCase("string") && !modifiedPatient.getPatientGender().equalsIgnoreCase(existingPatient.getPatientGender()))) {
            existingPatient.setPatientGender(modifiedPatient.getPatientGender());
            isUpdated = true;
        }
        // checking and updating blood group
        if ((existingPatient.getPatientBloodGroup() == null && modifiedPatient.getPatientBloodGroup() != null) || (modifiedPatient.getPatientBloodGroup() != null && existingPatient.getPatientBloodGroup() != null && !modifiedPatient.getPatientBloodGroup().isBlank() && !modifiedPatient.getPatientBloodGroup().equalsIgnoreCase("string") && !modifiedPatient.getPatientBloodGroup().equalsIgnoreCase(existingPatient.getPatientBloodGroup()))) {
            existingPatient.setPatientBloodGroup(modifiedPatient.getPatientBloodGroup());
            isUpdated = true;
        }
        // checking and updating dob
        if ((existingPatient.getPatientDob() == null && modifiedPatient.getPatientDob() != null) || (modifiedPatient.getPatientDob() != null && existingPatient.getPatientDob() != null && !modifiedPatient.getPatientDob().isBlank() && !modifiedPatient.getPatientDob().equalsIgnoreCase("string") && !modifiedPatient.getPatientDob().equalsIgnoreCase(existingPatient.getPatientDob()))) {
            existingPatient.setPatientDob(modifiedPatient.getPatientDob());
            isUpdated = true;
        }

        // checking and updating phone number
        if ((existingPatient.getPatientPhoneNumber() == null && modifiedPatient.getPatientPhoneNumber() != null) || (modifiedPatient.getPatientPhoneNumber() != null && !modifiedPatient.getPatientPhoneNumber().equals("") && !existingPatient.getPatientPhoneNumber().equalsIgnoreCase(modifiedPatient.getPatientPhoneNumber()))) {
            existingPatient.setPatientPhoneNumber(modifiedPatient.getPatientPhoneNumber());
            isUpdated = true;
        }
        // checking and updating email
        if ((existingPatient.getPatientEmail() == null && modifiedPatient.getPatientEmail() != null)
                || (modifiedPatient.getPatientEmail() != null
                && !modifiedPatient.getPatientEmail().equals("")
                && !modifiedPatient.getPatientEmail().equalsIgnoreCase("string")
                && !existingPatient.getPatientEmail().equalsIgnoreCase(modifiedPatient.getPatientEmail()))) {
            existingPatient.setPatientEmail(modifiedPatient.getPatientEmail());
            isUpdated = true;
        }
        // checking and updating city
        if ((existingPatient.getCity() == null && modifiedPatient.getCity() != null) || (modifiedPatient.getCity() != null && !modifiedPatient.getCity().equals("") && !existingPatient.getCity().equalsIgnoreCase(modifiedPatient.getCity()))) {
            existingPatient.setCity(modifiedPatient.getCity());
            isUpdated = true;
        }
        // checking and updating state
        if (existingPatient.getState() == null && modifiedPatient.getState() != null || (modifiedPatient.getState() != null && !modifiedPatient.getState().equals("") && !existingPatient.getState().equalsIgnoreCase(modifiedPatient.getState()))) {
            existingPatient.setState(modifiedPatient.getState());
        }
        // checking and updating pin code
        if ((existingPatient.getPinCode() == null && modifiedPatient.getPinCode() != null) || (modifiedPatient.getPinCode() != null && !modifiedPatient.getPinCode().equals("") && !existingPatient.getPinCode().equalsIgnoreCase(modifiedPatient.getPinCode()))) {
            existingPatient.setPinCode(modifiedPatient.getPinCode());
            isUpdated = true;
        }

        // always updating symptoms
        existingPatient.setPatientSymptoms(modifiedPatient.getPatientSymptoms());
        if (isUpdated) {
            logger.info("Updated: Patient's record in db");
            return patientRapo.save(existingPatient);
        } else {
            logger.info("Not Updated: Patient's record is already up to date");
            return existingPatient;
        }
    }

}
