package com.stackroute.registrationservice.service;

import com.stackroute.registrationservice.entity.User;
import com.stackroute.registrationservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class RegistrationServiceImp implements RegistrationServices{



        @Autowired
        private UserRepo repository;

        public String createUser(User user) {

        boolean result=validate(user);
        if(result)
        {
            repository.save(user);
            return "user created with id : " +user.getUserId();
        }
        else
            return "Password is weak, user not created" ;


    }

        public boolean validate(User user) {

        boolean result=false;
        if(user.getPassword().equals(user.getConfirm_password()))
        {
            Pattern p=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
            Matcher m =p.matcher(user.getPassword());

            result=m.matches();


        }
        return result;

    }

        public List<User> getUser() {
        List<User> userList = repository.findAll();
        return userList;
    }

        public List<User> getByemailId(String email_id) {
        return repository.findByEmailId(email_id);
    }
    }

