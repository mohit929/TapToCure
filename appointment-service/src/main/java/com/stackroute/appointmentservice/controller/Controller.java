package com.stackroute.appointmentservice.controller;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointmentservice")
public class Controller {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // Tested
    // this method will get all existing appointment record in db
    // and returns the List of appointment if found, else it returns null
    @GetMapping("/appointment")
    public List<Appointment> getAppointment() {
        try {
            return appointmentService.getAppointment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // this method will search an existing appointment record in db based on particular appointmentId
    // and returns the object of appointment if found, else it returns null
    @GetMapping("/appointment/{appointmentId}")
    public Appointment getAppointment(@PathVariable int appointmentId) {
        try {
            return appointmentService.getAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // this method will delete an existing appointment record in db based on particular appointmentId
    // and returns the deleted object of appointment if deleted, else it returns null
    @DeleteMapping("/appointment/{appointmentId}")
    public Appointment deleteAppointment(@PathVariable int appointmentId) {
        try {
            return appointmentService.deleteAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // this method will create an appointment record in db
    // and returns the created object of appointment if created, else it returns null
    @PostMapping("/appointment")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    // this method will update an already existing appointment record in db based on particular appointmentId
    // and returns the updated object of appointment if updated, else it returns null
    @PutMapping("/appointment")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        try
        {
            return appointmentService.updateAppointment(appointment);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
