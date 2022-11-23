package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    public Appointment deleteAppointment(int appointmentId);

    public Appointment getAppointment(int appointmentId);

    public List<Appointment> getAppointment();
}
