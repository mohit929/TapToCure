package com.stackroute.patientservice.controller;

import com.stackroute.patientservice.exception.NoPatientRecordsPresentException;
import com.stackroute.patientservice.exception.PatientNotFoundException;
import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.rabbitmqpublisher.RabbitMqPublisher;
import com.stackroute.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patientService")
public class PatientController {

    @Autowired
    private RabbitMqPublisher rabbitMqPublisher;
    @Autowired
    private PatientService service;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/registerPatient")
    public String registerPatient(@RequestBody Patient patient){
        try{
            return service.registerPatient(patient);
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/updatePatientDetails")
    public String updatePatientDetails(@RequestBody Patient patient) throws Exception {
        String message = null;
        boolean flag = false;
        try{
            message =  service.updatePatientDetails(patient);
        }catch (PatientNotFoundException noPatient){
            return noPatient.getMessage();
        }
        return message;
    }

    @GetMapping("/getPatientDetails/{patientId}")
    public String getPatientDetails(@PathVariable String patientId) throws Exception {
           try{
               rabbitMqPublisher.send(service.getPatientDetailsRabbit(patientId));
               return service.getPatientDetails(patientId);
           }catch (PatientNotFoundException e){
               return e.getMessage();
           }
    }

    @GetMapping("/getAllPatientDetails")
    public List<Patient> getAllPatientDetails() throws NoPatientRecordsPresentException {
        return service.getAllPatientDetails();
    }

    @DeleteMapping("/deletePatient/{patientId}")
    public String removePatient(@PathVariable String patientId){
        try{
            return service.deletePatient(patientId);
        } catch (PatientNotFoundException e) {
            return e.getMessage();
        }
    }

}
