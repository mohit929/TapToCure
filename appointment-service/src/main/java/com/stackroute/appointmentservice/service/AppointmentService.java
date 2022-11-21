package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Appointment;

import java.util.Optional;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    Optional<Appointment> deleteAppointment(int appointmentId);

    Optional<Appointment> searchAppointment(int appointmentId);
}
