package com.stackroute.clinicservice.repo;

import com.stackroute.clinicservice.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface appointmentRepository extends MongoRepository<Appointment,Integer> {
}
