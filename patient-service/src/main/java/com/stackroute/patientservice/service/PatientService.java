package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.NoPatientRecordsPresentException;
import com.stackroute.patientservice.exception.PatientNotFoundException;
import com.stackroute.patientservice.model.Patient;

import java.util.List;

public interface PatientService {
    String registerPatient(Patient patient) throws Exception;
    String getPatientDetails(String patientId) throws Exception;
    Patient getPatientDetailsRabbit(String patientId);
    String updatePatientDetails(Patient patient) throws Exception;
    List<Patient> getAllPatientDetails() throws NoPatientRecordsPresentException;
    boolean isEmailExists(String patient) throws Exception;
    String deletePatient(String patientId) throws PatientNotFoundException;
}
