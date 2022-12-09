package com.stackroute.patientservice.rabbitmqdto;

import com.stackroute.patientservice.rabbitmqdto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String userId;
    private String userName;
    private String emailId;
    private String phoneNo;
}
