package com.stackroute.appointmentservice.exception;

public class AppointmentAlreadyBookedException extends Exception{
    public AppointmentAlreadyBookedException(String message)
    {
        super(message);
    }
}
