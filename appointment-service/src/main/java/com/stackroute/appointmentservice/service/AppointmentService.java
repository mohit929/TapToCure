package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.appointmentservice.model.Appointment;

import java.util.List;

public interface AppointmentService {
    public List<Appointment> getAppointment() throws AppointmentNotFoundException, Exception;

    public Appointment getAppointment(int appointmentId) throws AppointmentNotFoundException, Exception;

    public Appointment deleteAppointment(int appointmentId) throws AppointmentNotFoundException, Exception;

    Appointment bookAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment) throws Exception;
}
