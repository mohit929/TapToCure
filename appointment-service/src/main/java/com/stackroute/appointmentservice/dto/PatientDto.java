package com.stackroute.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto{
    private String patientId;
    private String patientName;
    private String patientGender;
    private String patientBloodGroup;
    private String patientDob;
    private String patientPhoneNumber;
    private String patientEmail;
    private String city;
    private String state;
    private String pinCode;
    private String patientSymptoms;
}
