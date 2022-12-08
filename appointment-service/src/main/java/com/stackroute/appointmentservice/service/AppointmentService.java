package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.*;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;

import java.text.ParseException;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment) throws AppointmentAlreadyExistsException, CloneNotSupportedException, ParseException, InvalidDateTimeException;

    Appointment bookAppointment(Appointment appointment) throws AppointmentNotExistsException, AppointmentAlreadyBookedException, CloneNotSupportedException;

    Appointment updateAppointment(Appointment appointment) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException, CloneNotSupportedException;

    Appointment cancelAppointment(int appointmentId) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException;

    Appointment deleteAppointment(int appointmentId) throws AppointmentNotExistsException;

    List<Appointment> getAppointment() throws AppointmentNotExistsException;

    Appointment getAppointment(int appointmentId) throws AppointmentNotExistsException;

    List<Appointment> getAvailableAppointment() throws AppointmentNotExistsException;

    List<Appointment> getAppointment(int patientId, AppointmentStatus appointmentStatus) throws AppointmentNotExistsException;
}
