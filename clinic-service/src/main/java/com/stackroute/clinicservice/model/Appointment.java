package com.stackroute.clinicservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointment_detail")
public class Appointment {
    @Id
    private String Appointment_id;
    @Field("appointment_date")
    private String appointmentDate;
    @Field("appointment_time")
    private String appointmentTime;
    @Field("appointment_status")
    private AppointmentStatus appointmentStatus;

}
