package com.stackroute.patientservice.repository;

import com.stackroute.patientservice.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}
