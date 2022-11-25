package com.stackroute.appointmentservice.controller;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyBookedException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.AppointmentNotExistsException;
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

    @PostMapping("/appointment/create")
    public Appointment createAppointment(@RequestBody Appointment appointment)
    {
        try {
            return appointmentService.createAppointment(appointment);
        } catch (AppointmentAlreadyExistsException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/appointment/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment)
    {
        try {
            return appointmentService.bookAppointment(appointment);
        } catch (AppointmentNotExistsException | AppointmentAlreadyBookedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/appointment")
    public Appointment updateAppointment(@RequestBody Appointment appointment)
    {
        try
        {
            return appointmentService.updateAppointment(appointment);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/appointment/cancel/{appointmentId}")
    public Appointment cancelAppointment(@PathVariable  int appointmentId)
    {

        try {
            return appointmentService.cancelAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/appointment/{appointmentId}")
    public Appointment deleteAppointment(@PathVariable int appointmentId)
    {
        try {
            return appointmentService.deleteAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/appointment")
    public List<Appointment> getAppointment()
    {
        try {
            return appointmentService.getAppointment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @GetMapping("/appointment/{appointmentId}")
    public Appointment getAppointment(@PathVariable int appointmentId)
    {
        try {
            return appointmentService.getAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
