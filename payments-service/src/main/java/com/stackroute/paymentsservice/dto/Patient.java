package com.stackroute.paymentsservice.dto;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String patientName;

    public Patient(int patientId) {
        this.patientId = patientId;
    }
    public Patient(String patientName) {
        this.patientName = patientName;
    }

    //    private String patientGender;
//    private String patientBloodGroup;
//    private Date patientDob;
//    private String patientPhoneNumber;
//    private String patientEmail;
//    private String City;
//    private String State;
//    private String PinCode;
//    private String patientSymptoms;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(int patientId, String patientName) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
	}
    
    
}
