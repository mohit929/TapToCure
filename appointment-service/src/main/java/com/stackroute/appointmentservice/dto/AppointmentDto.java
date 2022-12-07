package com.stackroute.appointmentservice.dto;

import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Component
public class AppointmentDto {
    @Id
=======

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {
>>>>>>> 5e5bdc87d573540e4ca573cafc35c6e49ee4f412
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;

    // enum reference: for custom status values
    private AppointmentStatus appointmentStatus = AppointmentStatus.AVAILABLE;

<<<<<<< HEAD
    @OneToOne
    @JoinColumn(name = "patientId") // name= "primary key of second table"
    private Patient patientDetails;

    public AppointmentDto(int appointmentId, Patient patient) {
        this.appointmentId = appointmentId;
        this.patientDetails=patient;
    }

	public AppointmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentDto(int appointmentId, String appointmentDate, String appointmentTime,
			AppointmentStatus appointmentStatus, Patient patientDetails) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.appointmentStatus = appointmentStatus;
		this.patientDetails = patientDetails;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Patient getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(Patient patientDetails) {
		this.patientDetails = patientDetails;
	}
    
    

  }
=======
    private Patient patientDetails;

}
>>>>>>> 5e5bdc87d573540e4ca573cafc35c6e49ee4f412

