package com.stackroute.appointmentservice.repo;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// repository: to perform crud operations on Appointment object
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
  /*
  custom repository method to get only AVAILABLE appointments from db
   */
  List<Appointment> findByAppointmentStatusIn(List<AppointmentStatus> appointmentStatus);

  /*
  custom repository method to get only appointments of specific patient
   */
  List<Appointment> findByPatientDetailsAndAppointmentStatus(Patient patientDetails,AppointmentStatus appointmentStatus);
}
