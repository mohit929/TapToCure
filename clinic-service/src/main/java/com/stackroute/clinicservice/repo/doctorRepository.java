package com.stackroute.clinicservice.repo;

import com.stackroute.clinicservice.model.DoctorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface doctorRepository extends MongoRepository<DoctorDetail,Integer> {

    /*Optional<DoctorDetail> findByName(String name);*/
}
