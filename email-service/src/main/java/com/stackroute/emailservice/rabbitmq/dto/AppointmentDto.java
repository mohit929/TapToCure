package com.stackroute.emailservice.rabbitmq.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Data
public class AppointmentDto {
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentStatus;
    private Patient patientDetails;
}

