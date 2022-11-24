package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Patient;

public interface PatientService {
    public boolean isPatientExists(Patient patient);

    public Patient getPatient(int patientId);

    public Patient addPatient(Patient patient);

}
