package com.stackroute.clinicservice.repo;

import com.stackroute.clinicservice.model.ClinicDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface clinicRepository extends MongoRepository<ClinicDetail,Integer> {
}
