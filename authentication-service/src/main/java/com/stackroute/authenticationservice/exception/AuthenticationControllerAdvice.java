package com.stackroute.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AuthenticationControllerAdvice  {

    @ExceptionHandler(InvalidCredentialsException.class)
    public final ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex, WebRequest webRequest) {
        ExceptionSchema exceptionSchema =new ExceptionSchema(new Date(),400,"Bad Request",ex.getMessage()
                +",Try with another credentials",webRequest.getDescription(false));
        return new ResponseEntity<ExceptionSchema>(exceptionSchema, HttpStatus.BAD_REQUEST);
    }
}
