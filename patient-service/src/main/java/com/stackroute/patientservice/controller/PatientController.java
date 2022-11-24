package com.stackroute.patientservice.controller;

import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/patientService")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/registerPatient")
    public String registerPatient(@RequestBody Patient patient){
        try{
            if(service.validateEmail(patient.getPatientEmail())){
                return service.registerPatient(patient);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/updatePatientDetails")
    public Patient updatePatientDetails(@RequestBody Patient patient){
        try{
            service.validateEmail(patient.getPatientEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return service.updatePatientDetails(patient);
        }
        return null;
    }

    @GetMapping("/getPatientDetails/{patientId}")
    public Optional<Patient> getPatientDetails(@PathVariable String patientId){
        return service.getPatientDetails(patientId);
    }

    @DeleteMapping("/deletePatient/{patientId}")
    public String deletePatient(@PathVariable String patientId){
        return service.deletePatient(patientId);
    }

}
