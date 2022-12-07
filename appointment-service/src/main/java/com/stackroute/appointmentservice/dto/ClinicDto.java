package com.stackroute.appointmentservice.dto;

import com.stackroute.appointmentservice.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {
    private String clinicId;
    private List<Appointment> appointments;
}
