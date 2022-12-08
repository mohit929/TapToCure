package com.stackroute.emailservice.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "appointment_detail")
public class Appointment {
    @Id
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;

    // enum reference: for custom status values

    private AppointmentStatus appointmentStatus = AppointmentStatus.AVAILABLE;

    @OneToOne
    @JoinColumn(name = "patientId") // name= "primary key of second table"
    @Autowired
    private Patient patientDetails;

    public Appointment(int appointmentId,Patient patient) {
        this.appointmentId = appointmentId;
        this.patientDetails=patient;
    }

  }

