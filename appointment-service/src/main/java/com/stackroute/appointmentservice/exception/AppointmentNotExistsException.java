package com.stackroute.appointmentservice.exception;

public class AppointmentNotExistsException extends Exception {
    public AppointmentNotExistsException(String message) {
        super(message);
    }
}
