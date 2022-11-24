package com.stackroute.patientservice.service;

import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public String registerPatient(Patient patient) {
        repository.insert(patient);
        return "Patient registered successfully with id : " + patient.getPatientId();
    }

    @Override
    public Optional<Patient> getPatientDetails(String patientId) {
        return repository.findById(patientId);
    }

    @Override
    public boolean validateEmail(String email) throws Exception {
        List<Patient> patientList = repository.findAll();
        for (Patient tempPatient : patientList) {
            if (tempPatient.getPatientEmail().equals(email)) {
                throw new Exception("email already exists!!");
            }
        }
        // if email doesn't exist
        return true;
    }

    @Override
    //need to look on
    public Patient updatePatientDetails(Patient patient) {
        Patient tempPatient = repository.findById(patient.getPatientId()).get();

        tempPatient.setPatientId(patient.getPatientId());
        tempPatient.setPatientName(patient.getPatientName());
        tempPatient.setPatientEmail(patient.getPatientEmail());
        tempPatient.setPatientGender(patient.getPatientGender());
        tempPatient.setPatientDob(patient.getPatientDob());
        tempPatient.setPatientBloodGroup(patient.getPatientBloodGroup());
        tempPatient.setPatientSymptoms(patient.getPatientSymptoms());
        tempPatient.setCity(patient.getCity());
        tempPatient.setState(patient.getState());
        tempPatient.setPatientPhoneNumber(patient.getPatientPhoneNumber());
        tempPatient.setPinCode(patient.getPinCode());

        //save updated patient details
        repository.save(tempPatient);
        return tempPatient;
    }

    @Override
    // need to look on
    public String deletePatient(String patientId) {
        repository.deleteById(patientId);
        return "Patient record deleted successfully having id : " + patientId;
    }
}
