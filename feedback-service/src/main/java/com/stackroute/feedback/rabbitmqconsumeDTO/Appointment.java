package com.stackroute.feedback.rabbitmqconsumeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
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
    private String appointmentId;
    @Field("appointment_date")
    private String appointmentDate;
    @Field("appointment_time")
    private String appointmentTime;
    @Field("appointment_status")
    private AppointmentStatus appointmentStatus;

}
