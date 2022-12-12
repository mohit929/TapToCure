package com.stackroute.registrationservice.controller;

import com.stackroute.registrationservice.config.Config;
import com.stackroute.registrationservice.entity.Email;
import com.stackroute.registrationservice.entity.Reset;

import com.stackroute.registrationservice.publisher.RabbitmqPublisher;
import com.stackroute.registrationservice.rabbitmqResponseDTO.OtpDto;
import com.stackroute.registrationservice.repo.UserRepo;
import com.stackroute.registrationservice.entity.User;
import com.stackroute.registrationservice.service.RegistrationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/register")
public class UserController {

    private int otpno;
    @Autowired
    private RabbitmqPublisher rabbitmqPublisher;

    @Autowired
    private UserRepo repository;


    @Autowired
    private UserRepo repo;
    @Autowired
    private RegistrationServiceImp service;


    @PostMapping("/publishPatient")
    public String publishPatient(@RequestBody User user)
    {
        rabbitmqPublisher.send(user);
        return "Successful Published!!";
    }
    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        User r = service.getByemailId(user.getEmailId());
        if(r==null)
        {
            try {
                service.createUser(user);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            rabbitmqPublisher.send(user);
            rabbitmqPublisher.sendToEmailQueue(user);
            rabbitmqPublisher.sendToAuthentication(user);

            return "Successful Registered!!";

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
    public Optional<User> getUserById(@PathVariable String id)
    {
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
    public User getByemailId(@RequestParam(value = "email_id") String email_id)
    {
        return service.getByemailId(email_id);
    }

    //@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody Email emailModel) {
        service.sendEmail(emailModel.getMessage(), emailModel.getSubject(), emailModel.getTo());
        System.out.println(emailModel);
        return ResponseEntity.ok("Done...");
    }

    //to send the OTP
    @PostMapping("/otp")
    public String sendOTP(@RequestBody String emailId) {
        int response =service.sendOtp(emailId);
        System.out.println(response);
        otpno=response;
        rabbitmqPublisher.sendOtp(emailId,otpno);
        return "OTP sent to "+emailId+" succesfully";

    }

    //resetpassword
    @PutMapping("/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody Reset reset) {
        User u = service.getByemailId(reset.getEmailId());

        if((reset.getOld_password().equals(u.getPassword())) && (reset.getOtp()==otpno))
        {
            u.setPassword(reset.getNew_password());
            u.setConfirm_password(reset.getNew_password());

            System.out.println(u.getPassword());
            service.createUser(u);
            rabbitmqPublisher.sendToAuthentication(u);
            return ResponseEntity.ok("Done...");
        }
        else
        {
            return ResponseEntity.ok("old password or OTP is incorrect...");

        }


    }

}
