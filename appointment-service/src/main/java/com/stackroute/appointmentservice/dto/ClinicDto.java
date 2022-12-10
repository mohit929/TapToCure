package com.stackroute.appointmentservice.dto;

import com.stackroute.appointmentservice.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {
    //private int clinicId;
    //private List<Appointment> appointments;

    private int clinicID;
    private String clinicName;
    private String state;
    private String city;
    private String area;
    private String buildingNumber;
    private int totalSlots;
    private String openingTime;
    private String closingTime;
    private int serviceDays;
    static int totalOccupiedSlots;
    private String doctorId;
    private String doctorName;
    private String specialization;
    private String doctor_contact;
    private String doctor_mail;
    private List<Appointment> appointment;
}
