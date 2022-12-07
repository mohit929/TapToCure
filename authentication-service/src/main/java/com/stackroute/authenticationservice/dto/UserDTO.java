package com.stackroute.authenticationservice.dto;

import com.stackroute.authenticationservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String email;
    private String password;
    private String username;
    private Role role;
}
