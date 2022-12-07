package com.stackroute.clinicservice.exception;

public class ClinicDoesNotExistsException extends Exception{
    public ClinicDoesNotExistsException(String message){
        super(message);
    }
}
