package com.stackroute.patientservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "patient_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
    @Id
    @Field("_id")
    @Schema(hidden = true)
    private String patientId;
    @Field("patient_name")
    @Schema(hidden = true)
    private String patientName;
    @Field("patient_gender")
    private String patientGender;
    @Field("patient_blood_group")
    private String patientBloodGroup;
    @Field("patient_dob")
    private String patientDob;
    @Field("patient_phone_number")
    @Schema(hidden = true)
    private String patientPhoneNumber;
    @Field("patient_email")
    @Schema(hidden = true)
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
