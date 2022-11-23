package com.stackroute.clinicservice.controller;

import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/save-appointment")
    public String saveAppointment(@RequestBody Appointment appointment){
        return appointmentService.createAppointment(appointment);
    }
    @GetMapping("/getAppointment")
    public List<Appointment> getAppointment(){
        return appointmentService.getAppointment();

    }
}
