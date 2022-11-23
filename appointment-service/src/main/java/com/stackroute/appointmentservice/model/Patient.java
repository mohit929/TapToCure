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
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int patientId;
    private String patientName;

//    private String patientGender;
//    private String patientBloodGroup;
//    private Date patientDob;
//    private String patientPhoneNumber;
//    private String patientEmail;
//    private String City;
//    private String State;
//    private String PinCode;
//    private String patientSymptoms;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
