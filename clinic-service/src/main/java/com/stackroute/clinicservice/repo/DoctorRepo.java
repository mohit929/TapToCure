package com.stackroute.clinicservice.repo;

import com.stackroute.clinicservice.rabbitmqResponseDTO.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends MongoRepository<UserDTO,Integer> {
}
