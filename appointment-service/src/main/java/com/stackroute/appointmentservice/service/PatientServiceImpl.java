package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepo patientRapo;

    @Override
    public Patient addPatient(Patient patient) {
        return patientRapo.save(patient);
    }

    @Override
    public boolean isPatientExists(Patient patient) {
        return patientRapo.existsById(patient.getPatientId());
    }

    @Override
    public Patient getPatient(int patientId) {
        return patientRapo.getOne(patientId);
    }


}
