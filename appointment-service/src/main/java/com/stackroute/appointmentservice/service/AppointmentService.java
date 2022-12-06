package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyBookedException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyCancelledException;
import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.AppointmentNotExistsException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;

import java.util.List;

public interface AppointmentService {

    public Appointment createAppointment(Appointment appointment) throws AppointmentAlreadyExistsException, CloneNotSupportedException;

    public Appointment bookAppointment(Appointment appointment) throws AppointmentNotExistsException, AppointmentAlreadyBookedException, CloneNotSupportedException;

    public Appointment updateAppointment(Appointment appointment) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException, CloneNotSupportedException;

    public Appointment cancelAppointment(int appointmentId) throws AppointmentNotExistsException, AppointmentAlreadyCancelledException;

    public Appointment deleteAppointment(int appointmentId) throws AppointmentNotExistsException;

    public List<Appointment> getAppointment() throws AppointmentNotExistsException;

    public Appointment getAppointment(int appointmentId) throws AppointmentNotExistsException;

    public List<Appointment> getAvailableAppointment() throws Exception;

    public List<Appointment> findByPatientDetailsAndAppointmentStatus(int patientId, AppointmentStatus appointmentStatus) throws Exception;
}
