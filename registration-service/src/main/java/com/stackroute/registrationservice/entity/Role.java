package com.stackroute.registrationservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public enum Role {
    @JsonProperty("Doctor")
    Doctor,
    @JsonProperty("Patient")
    Patient,


}
