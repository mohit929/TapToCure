package com.stackroute.appointmentservice.dto;

import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;

    // enum reference: for custom status values
    private AppointmentStatus appointmentStatus = AppointmentStatus.AVAILABLE;

    private Patient patientDetails;

}

