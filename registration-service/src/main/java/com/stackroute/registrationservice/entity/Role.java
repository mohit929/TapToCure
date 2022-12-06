package com.stackroute.registrationservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("Doctor")
    Doctor,
    @JsonProperty("Patient")
    Patient,

}
