package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.appointmentservice.model.Appointment;

import java.util.List;

public interface AppointmentService {
    public List<Appointment> getAppointment() throws AppointmentNotFoundException;

    public Appointment getAppointment(int appointmentId) throws AppointmentNotFoundException;

    public Appointment deleteAppointment(int appointmentId) throws AppointmentNotFoundException;

    public Appointment bookAppointment(Appointment appointment);

    public Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException;
}
