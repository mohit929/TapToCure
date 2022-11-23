package com.stackroute.authenticationservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
//    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping("/accessDoctor")
    public String  accessDoctor()
    {
        return "Doctor can access this";
    }
//    @PreAuthorize("hasAuthority('patient')")
    @GetMapping("/accessPatient")
    public String accessPatient()
    {
        return "Patient can access this";
    }
}
