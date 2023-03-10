package com.stackroute.authenticationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException{
    private String message;
}
