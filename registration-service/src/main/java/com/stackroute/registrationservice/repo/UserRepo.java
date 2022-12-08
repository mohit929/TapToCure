package com.stackroute.registrationservice.repo;

import com.stackroute.registrationservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {


    List<User> findByEmailId(String emailID);
}
