package com.stackroute.authenticationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@ToString
public class ExceptionSchema {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
