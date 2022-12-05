package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.InvalidDateTimeException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Clinic;

import java.text.ParseException;

public interface ClinicService {
    Clinic createDummyClinicAppointment();
}
