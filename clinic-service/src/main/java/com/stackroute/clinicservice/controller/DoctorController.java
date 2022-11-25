package com.stackroute.clinicservice.controller;

import com.stackroute.clinicservice.model.DoctorDetail;
import com.stackroute.clinicservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class DoctorController {
    @Autowired
    private DoctorService service;


    @PostMapping("/save")
    public String saveDoctor(@RequestBody DoctorDetail doctorDetail){
        return service.createDoctor(doctorDetail);
    }
    @GetMapping("/getDoctorDetail")
    public List<DoctorDetail> getDoctorDetail(){
        return service.getDoctorDetail();

    }
    @GetMapping("/getDoctorDetail/{doctorId}")
    public Optional<DoctorDetail> searchDoctor(@PathVariable int doctorId) {
        return service.searchDoctor(doctorId);
    }

    @GetMapping("/getDoctorDetailByName/{name}")
    public Optional<DoctorDetail> findByDoctorName(@PathVariable String name){
        return service.getDoctorByName(name);
    }






    //@PutMapping("/updateStatus/{doctorId)")
    //public String updateStatus(@RequestBody DoctorDetail doctorDetail, @PathVariable int doctorId){
      //  service.updateStatus(doctorDetail,doctorId);
        //return "Updated the Status";
   // }

}
