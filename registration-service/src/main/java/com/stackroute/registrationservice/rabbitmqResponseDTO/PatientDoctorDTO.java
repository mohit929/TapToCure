package com.stackroute.registrationservice.rabbitmqResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDoctorDTO {


        private String userId;

        private String userName;
        private String emailId;
        private String phoneNo;


}
