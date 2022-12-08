package com.stackroute.clinicservice.rabbitmqResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {



    private String userId;
    private String userName;
    private String emailId;
    private String phoneNo;

}
