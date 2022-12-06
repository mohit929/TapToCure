package com.stackroute.patientservice.exception;

public class NoPatientRecordsPresentException extends RuntimeException{
    public NoPatientRecordsPresentException(String message){
        super(message);
    }
}
