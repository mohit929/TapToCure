package com.stackroute.paymentsservice.rabbitmqdto;


import org.springframework.stereotype.Component;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
public class Patient implements Cloneable {
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

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}

	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}

	public String getPatientDob() {
		return patientDob;
	}

	public void setPatientDob(String patientDob) {
		this.patientDob = patientDob;
	}

	public String getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	public void setPatientPhoneNumber(String patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPatientSymptoms() {
		return patientSymptoms;
	}

	public void setPatientSymptoms(String patientSymptoms) {
		this.patientSymptoms = patientSymptoms;
	}

	public Patient(int patientId, String patientName, String patientGender, String patientBloodGroup, String patientDob,
			String patientPhoneNumber, String patientEmail, String city, String state, String pinCode,
			String patientSymptoms) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientBloodGroup = patientBloodGroup;
		this.patientDob = patientDob;
		this.patientPhoneNumber = patientPhoneNumber;
		this.patientEmail = patientEmail;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.patientSymptoms = patientSymptoms;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientGender=" + patientGender
				+ ", patientBloodGroup=" + patientBloodGroup + ", patientDob=" + patientDob + ", patientPhoneNumber="
				+ patientPhoneNumber + ", patientEmail=" + patientEmail + ", city=" + city + ", state=" + state
				+ ", pinCode=" + pinCode + ", patientSymptoms=" + patientSymptoms + "]";
	}
    
}
