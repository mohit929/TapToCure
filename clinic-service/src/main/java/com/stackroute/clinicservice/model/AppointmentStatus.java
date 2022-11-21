package com.stackroute.clinicservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AppointmentStatus {
    @JsonProperty("available")
    Available,
    @JsonProperty("booked")
    Booked,
}

