package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyBookedException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyCancelledException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.AppointmentNotExistsException;
import com.stackroute.appointmentservice.model.Appointment;

import java.util.List;

public interface AppointmentService {

    public Appointment createAppointment(Appointment appointment) throws AppointmentAlreadyExistsException;

    public Appointment bookAppointment(Appointment appointment) throws AppointmentNotExistsException, AppointmentAlreadyBookedException;

    public Appointment updateAppointment(Appointment appointment) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException;

    public Appointment cancelAppointment(int appointmentId) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException;

    public Appointment deleteAppointment(int appointmentId) throws AppointmentNotExistsException;

    public List<Appointment> getAppointment() throws AppointmentNotExistsException;

    public Appointment getAppointment(int appointmentId) throws AppointmentNotExistsException;
}
