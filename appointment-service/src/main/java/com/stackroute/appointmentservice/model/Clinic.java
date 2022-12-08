package com.stackroute.appointmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    private String clinicId;
    private List<Appointment> appointments;
}
