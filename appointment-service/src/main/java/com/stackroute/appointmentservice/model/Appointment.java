package com.stackroute.appointmentservice.model;

import org.apache.tomcat.jni.Time;

import java.util.Date;

public class Appointment
{
    private int appointmentId;

    private String appointmentDate;
    private String appointmentTime;

    // for doctor's information
    private Clinic clinic;
    // for patient's information
    private Patient patient;
}
