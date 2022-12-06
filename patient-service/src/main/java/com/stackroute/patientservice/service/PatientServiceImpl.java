package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.*;
import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.rabbitmqconsumer.RabbitMqConsumer;
import com.stackroute.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private RabbitMqConsumer rabbitMqConsumer;


    @Override
    public String registerPatient(Patient patient) throws Exception {
        Patient tempPatient1 = rabbitMqConsumer.setPatientDetails();
        patient.setPatientId(tempPatient1.getPatientId());
        patient.setPatientEmail(tempPatient1.getPatientEmail());
        patient.setPatientPhoneNumber(tempPatient1.getPatientPhoneNumber());
        try {
            Optional<Patient> tempPatient = repository.findById(patient.getPatientId());
            if (!tempPatient.isEmpty()) {
                throw new PatientAlreadyExistsException("Patient already exists with id : " + patient.getPatientId());
            }
            if (isEmailExists(patient.getPatientEmail())) {
                repository.insert(patient);
            }
        } catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException(e.getMessage());
        }
        return "Patient registered successfully with id : " + patient.getPatientId();
    }

    @Override
    public String getPatientDetails(String patientId) throws PatientNotFoundException {
        Patient patient;

        try {
            patient = repository.findById(patientId).get();
        } catch (NoSuchElementException e) {
            throw new PatientNotFoundException("PatientDTO not found with id : " + patientId);
        }
        return patient.toString();
    }

    @Override
    public List<Patient> getAllPatientDetails() throws NoPatientRecordsPresentException {
        if (repository.findAll().isEmpty()) {
            throw new NoPatientRecordsPresentException("No PatientDTO records found!!");
        }
        return repository.findAll();
    }

    @Override
    public boolean isEmailExists(String email) throws Exception {
        List<Patient> patientList = repository.findAll();
        for (Patient tempPatient : patientList) {
            if (tempPatient.getPatientEmail().equals(email)) {
                throw new EmailAlreadyExistsException("email already exists!!");
            }
        }
        // if email doesn't exist
        return true;
    }

    @Override
    public String updatePatientDetails(Patient patient) throws Exception {
        Optional<Patient> optPatient = repository.findById(patient.getPatientId());
        Patient tempPatient = null;
        try {
            tempPatient = optPatient.get();
        } catch (NoSuchElementException e) {
            throw new PatientNotFoundException("PatientDTO with id : " + patient.getPatientId() + " does not exist!!");
        }
        repository.save(patient);
        return patient.toString();
    }

    @Override
    public String deletePatient(String patientId) throws PatientNotFoundException {
        try {
            if (repository.findById(patientId).get() != null) {
                repository.deleteById(patientId);
                return "PatientDTO record deleted successfully having id : " + patientId;
            }
        } catch (NoSuchElementException e) {
            throw new PatientNotFoundException("PatientDTO record not found!!");
        }
        return null;
    }

    @Override
    public Patient getPatientDetailsRabbit(String id){
        return repository.findById(id).get();
    }
}
