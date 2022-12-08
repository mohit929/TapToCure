package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepo patientRapo;
    @Autowired
    Patient patient;

    @Override
    public Patient addPatient(Patient patient) {
        if(!patientRapo.existsById(patient.getPatientId()))
        {
            System.out.println("Created: New Patient record");
            return patientRapo.save(patient);
        }
        return patient;
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
