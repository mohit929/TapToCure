package com.stackroute.clinicservice.controller;

import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.model.DoctorDetail;
import com.stackroute.clinicservice.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ClinicController {

        @Autowired
        private ClinicService clinicService;
        @PostMapping("/saveclinic")
        public String saveClinic(@RequestBody ClinicDetail clinicDetail){
            return clinicService.createClinic(clinicDetail);
        }
        @GetMapping("/getClinicDetail")
        public List<ClinicDetail> getClinicDetail(){
            return clinicService.getClinicDetail();
        }
        @GetMapping("/getClinicDetail/{clinicId}")
        public Optional<ClinicDetail> searchClinic(@PathVariable int clinicId) {
        return clinicService.searchClinic(clinicId);
        }
        @DeleteMapping("/getClinicDetail/{clinicId}")
        public String deleteClinicDetail(@PathVariable int clinicId)
        {
                return clinicService.deleteClinicDetail(clinicId);
        }


}

