package com.stackroute.authenticationservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class AuthenticationExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException, WebRequest webRequest)
    {   log.info("Inside the handler method of InvalidCredentials Exception");
        ExceptionSchema exceptionSchema=new ExceptionSchema(new Date(),400,"Bad Request", invalidCredentialsException.getMessage(),
                webRequest.getDescription(false));
        ResponseEntity <ExceptionSchema>responseEntity=new ResponseEntity(exceptionSchema,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
