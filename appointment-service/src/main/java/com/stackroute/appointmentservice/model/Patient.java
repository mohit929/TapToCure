package com.stackroute.appointmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient_detail")
public class Patient implements Cloneable {
    public Patient(int patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;
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
    //

    public Patient(int patientId) {
        this.patientId = patientId;
    }

    public Patient(String patientName) {
        this.patientName = patientName;
    }

    public Patient(int patientId, String patientName,String patientEmail,String patientPhoneNumber) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientEmail = patientEmail;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
