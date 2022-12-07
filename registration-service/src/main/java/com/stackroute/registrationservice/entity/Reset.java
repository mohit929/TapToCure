package com.stackroute.registrationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reset {



    private String emailId;

    private String old_password;

    private String new_password;

    private int otp;

}
