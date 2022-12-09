package com.stackroute.feedback.rabbitmqconsumeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("Doctor")
    Doctor,
    @JsonProperty("Patient")
    Patient,

}
