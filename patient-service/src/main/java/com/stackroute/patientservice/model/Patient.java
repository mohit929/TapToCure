package com.stackroute.patientservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Document(collection = "patient_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
    @Field("_id")
    private String patientId;
    @Field("patient_name")
    private String patientName;
    @Field("patient_gender")
    private String patientGender;
    @Field("patient_blood_group")
    private String patientBloodGroup;
    @Field("patient_dob")
    private String patientDob;
    @Field("patient_phone_number")
    private String patientPhoneNumber;
    @Field("patient_email")
    private String patientEmail;
    @Field("patient_city")
    private String city;
    @Field("patient_state")
    private String state;
    @Field("patient_pincode")
    private String pinCode;
    @Field("patient_symptoms")
    private String patientSymptoms;
}
