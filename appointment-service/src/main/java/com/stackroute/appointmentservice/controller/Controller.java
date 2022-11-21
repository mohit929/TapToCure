package com.stackroute.appointmentservice.controller;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/appointmentservice")
public class Controller
{
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/appointment")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @PutMapping("/appointment")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/appointment/{appointmentId}")
    public Optional<Appointment> deleteAppointment(@PathVariable int appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }

    @GetMapping("/appointment/{appointmentId}")
    public Optional<Appointment> searchAppointment(@PathVariable int appointmentId) {
        return appointmentService.searchAppointment(appointmentId);
    }
}
