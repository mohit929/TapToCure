package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where user_email=:email")
    public User findByUserEmail(@Param("email")String email);


}
