package com.stackroute.appointmentservice.exception;

public class AppointmentAlreadyCancelledException extends Exception {
    public AppointmentAlreadyCancelledException(String message)
    {
        super(message);
    }
}
