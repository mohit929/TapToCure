package com.stackroute.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class PatientDto {
    private String userId;
    private String emailId;
    private String phoneNo;
}
