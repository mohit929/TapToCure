package com.stackroute.appointmentservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "appointment_detail")
public class Appointment
{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    // enum reference: for custom status values
    private AppointmentStatus appointmentStatus;
    @OneToOne
    @JoinColumn(name = "patientId") // name= "primary key of second table"
    private Patient patientDetails;
}

// enum: for custom status values
enum AppointmentStatus {
    Available,
    Booked
};
