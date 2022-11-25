package com.stackroute.patientservice.controller;

import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
            if(service.isEmailExists(patient.getPatientEmail())){
                return service.registerPatient(patient);
            }
        }catch (Exception e){
            return e.getMessage();
        }

        return null;
    }

    @PostMapping("/updatePatientDetails")
    public Patient updatePatientDetails(@RequestBody Patient patient){
        try{
            service.isEmailExists(patient.getPatientEmail());
        } catch (Exception e) {
            return service.updatePatientDetails(patient);
        }
        return null;
    }

    @GetMapping("/getPatientDetails/{patientId}")
    public Optional<Patient> getPatientDetails(@PathVariable String patientId) throws Exception {
           return service.getPatientDetails(patientId);
    }

    @GetMapping("/getAllPatientDetails")
    public List<Patient> getAllPatientDetails(){
        return service.getAllPatientDetails();
    }

    @DeleteMapping("/deletePatient/{patientId}")
    public String removePatient(@PathVariable String patientId){
        try{
            return service.deletePatient(patientId);
        }catch(NoSuchElementException e){
            return e.getMessage();
        }
    }

}
