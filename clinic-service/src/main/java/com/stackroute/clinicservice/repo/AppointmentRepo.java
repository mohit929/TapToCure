package com.stackroute.clinicservice.repo;



import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.model.AppointmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// repository: to perform crud operations on Appointment object
@Repository
public interface AppointmentRepo extends MongoRepository<Appointment, Integer> {

}
