package com.stackroute.registrationservice.rabbitmqResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto {

        private String emailId;
        private int otpno;

}
