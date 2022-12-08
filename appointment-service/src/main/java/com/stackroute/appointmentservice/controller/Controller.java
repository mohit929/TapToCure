package com.stackroute.appointmentservice.controller;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.rabbitpublisher.Publisher;
import com.stackroute.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointmentservice")
public class Controller {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    Publisher publisher;

    @GetMapping("/appointment/home")
    public String home() {
        return "home";
    }

    @PostMapping("/appointment/create")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        try {
            return appointmentService.createAppointment(appointment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/appointment/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment a = appointmentService.bookAppointment(appointment);
            publisher.sendAppointment(a, "" + AppointmentStatus.BOOKED);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/appointment/reschedule")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment a = appointmentService.updateAppointment(appointment);
            publisher.sendAppointment(a, "UPDATED");
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/appointment/cancel/{appointmentId}")
    public Appointment cancelAppointment(@PathVariable int appointmentId) {

        try {
            Appointment a = appointmentService.cancelAppointment(appointmentId);
            publisher.sendAppointment(a, "" + AppointmentStatus.CANCELLED);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/appointment/delete/{appointmentId}")
    public Appointment deleteAppointment(@PathVariable int appointmentId) {
        try {
            return appointmentService.deleteAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/appointment/showAll")
    public List<Appointment> getAppointment() {
        try {
            return appointmentService.getAppointment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/appointment/showOne/{appointmentId}")
    public Appointment getAppointment(@PathVariable int appointmentId) {
        try {
            return appointmentService.getAppointment(appointmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/appointment/showOne/{patientId}/{appointmentStatus}")
    public List<Appointment> getAppointment(@PathVariable int patientId, @PathVariable AppointmentStatus appointmentStatus) {
        try {
            return appointmentService.getAppointment(patientId, appointmentStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/appointment/showAvailable")
    public List<Appointment> getAvailableAppointment() {
        try {
            return appointmentService.getAvailableAppointment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
