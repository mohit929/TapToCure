package com.stackroute.appointmentservice.repo;

import com.stackroute.appointmentservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo  extends JpaRepository<Patient, Integer> {
}
