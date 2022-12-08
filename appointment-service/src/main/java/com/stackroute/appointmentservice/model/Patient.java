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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;
    private String patientName;
    private String patientEmailId;
    private String patientPhoneNo;

    public Patient(int patientId) {
        this.patientId = patientId;
    }

    public Patient(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
