package com.stackroute.patientservice.rabbitmqdto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RoleDTO {
    @JsonProperty("Doctor")
    Doctor,
    @JsonProperty("Patient")
    Patient,
}
