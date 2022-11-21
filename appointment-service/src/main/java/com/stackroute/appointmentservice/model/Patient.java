package com.stackroute.appointmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="patient_detail")
public class Patient
{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;
    private String patientName;
}
