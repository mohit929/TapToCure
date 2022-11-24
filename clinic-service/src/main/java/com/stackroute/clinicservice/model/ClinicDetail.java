package com.stackroute.clinicservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clinic_detail")
public class ClinicDetail {
    @Id

    private int clinicID;
    @Field("clinic_name")
    private String clinicName;
    @Field("state")
    private String state;
    @Field("city")
    private String city;
    @Field("area")
    private String area;
    @Field("building_name")
    private String buildingNumber;
    @Field("total_slots")
    private int totalSlots;
    @Field("opening_time")
    private String openingTime;
    @Field("closing_time")
    private String closingTime;
    @Field("service_days")
    private int serviceDays;
    @Field("total_occupied_slots")
    static int totalOccupiedSlots;

}
