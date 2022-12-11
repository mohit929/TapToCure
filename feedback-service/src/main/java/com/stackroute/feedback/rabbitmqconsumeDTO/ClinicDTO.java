package com.stackroute.feedback.rabbitmqconsumeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDTO {
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
