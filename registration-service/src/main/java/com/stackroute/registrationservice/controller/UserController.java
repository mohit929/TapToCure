package com.stackroute.registrationservice.controller;

import com.stackroute.registrationservice.entity.User;
import com.stackroute.registrationservice.repo.UserRepo;
import com.stackroute.registrationservice.service.RegistrationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/register")
public class UserController
{
    @Autowired
    private UserRepo repo;
    @Autowired
    private RegistrationServiceImp service;

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        List<User> r = service.getByemailId(user.getEmailId());
        if(r.isEmpty())
        {
            service.createUser(user);
            return "Succesful";

        }
        return "User already registered with the given EMAIL-ID";
    }

    //find all users
    @GetMapping("/getUsers")
    public List<User> getUser(){
        return service.getUser();
    }

    //find by id
    @GetMapping("/findById/{id}")
    public Optional<User> getUserById(@PathVariable String id){
        return repo.findById(id);
    }

    //delete user by ID
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable String id){
        repo.deleteById(id);
        return "User Deleted with ID:"+id;
    }

    //find by email
    @GetMapping("/email")
    public List<User> getByemailId(@RequestParam(value = "email_id") String email_id)
    {
        return service.getByemailId(email_id);
    }

}