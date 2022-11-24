package com.stackroute.clinicservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "doctor_detail")
public class DoctorDetail {
    @Id
    private String doctorId;
    @Field("doctor_name")
    private String doctorName;
    @Field("specialization")
    private String specialization;


}
