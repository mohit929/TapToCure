package com.stackroute.patientservice.service;

import com.stackroute.patientservice.model.Patient;

import java.util.Optional;

public interface PatientService {
    String registerPatient(Patient patient);
    Optional<Patient> getPatientDetails(String patientId);
    Patient updatePatientDetails(Patient patient);

    boolean validateEmail(String patient) throws Exception;
    String deletePatient(String patientId);
}
