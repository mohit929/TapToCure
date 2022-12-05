package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Patient;

public interface PatientService {
    Patient addPatient(Patient patient);

    boolean isPatientExists(Patient patient);

    Patient getPatient(int patientId);
}
