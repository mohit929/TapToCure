package com.stackroute.appointmentservice.controller;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointmentservice")
public class Controller
{
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

    // this method will create an appointment record in db
    // and returns the created object of appointment if created, else it returns null
    @PostMapping("/appointment")
    public Appointment bookAppointment(@RequestBody Appointment appointment)
    {
        return appointmentService.bookAppointment(appointment);
    }

    // this method will update an already existing appointment record in db based on particular appointmentId
    // and returns the updated object of appointment if updated, else it returns null
    @PutMapping("/appointment")
    public Appointment updateAppointment(@RequestBody Appointment appointment)
    {
        return appointmentService.updateAppointment(appointment);
    }

    // this method will delete an existing appointment record in db based on particular appointmentId
    // and returns the deleted object of appointment if deleted, else it returns null
    @DeleteMapping("/appointment/{appointmentId}")
    public Appointment deleteAppointment(@PathVariable int appointmentId)
    {
        return appointmentService.deleteAppointment(appointmentId);
    }

    // this method will search an existing appointment record in db based on particular appointmentId
    // and returns the object of appointment if found, else it returns null
    @GetMapping("/appointment/{appointmentId}")
    public Appointment getAppointment(@PathVariable int appointmentId)
    {
        return appointmentService.getAppointment(appointmentId);
    }

    // this method will get all existing appointment record in db
    // and returns the List of appointment if found, else it returns null
    @GetMapping("/appointment")
    public List<Appointment> getAppointment()
    {
        return appointmentService.getAppointment();
    }
}
