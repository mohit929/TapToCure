package com.stackroute.appointmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "appointment_detail")
public class Appointment {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    // enum reference: for custom status values
    private AppointmentStatus appointmentStatus = AppointmentStatus.AVAILABLE;

    public Appointment(int appointmentId,Patient patient) {
        this.appointmentId = appointmentId;
        this.patientDetails=patient;
    }

    @OneToOne
    @JoinColumn(name = "patientId") // name= "primary key of second table"
    private Patient patientDetails;
}

