package com.stackroute.appointmentservice.exception;

public class AppointmentAlreadyExistsException extends Exception {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}

