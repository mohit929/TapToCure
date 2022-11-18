package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Clinic;
import com.stackroute.appointmentservice.model.Patient;

import java.util.List;

public interface AppointmentService
{
    // It will return list of Appointment object if clinic has available slots else will return null
    public List<Appointment> checkAvailability(Clinic clinic);

    // It will book an appointment and return an "Appointment" object
    public Appointment bookAppointment(Clinic clinic, Patient patient, Appointment appointment);

    // It will cancel an appointment
    // and decrease the number of totalOccupiedSlots
    public boolean cancelAppointment(Appointment appointment);

    // It will reschedule an appointment
    // 1. check availability for new slot
    // 2. book the selected new slot
    // 3. cancel the old slot
    public Appointment rescheduleAppointment(Appointment appointment);
}
