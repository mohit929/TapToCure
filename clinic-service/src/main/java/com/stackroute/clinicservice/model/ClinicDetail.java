package com.stackroute.clinicservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


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
    @Field("doctor_id")
    private String doctorId;
    @Field("doctor_name")
    private String doctorName;
    @Field("specialization")
    private String specialization;
    @Field("doctor_contact")
    private String doctor_contact;
    @Field("doctor_mail")
    private String doctor_mail;
    @Field("applist")
    private List<Appointment> appointment;
    public ClinicDetail(int clinicID, String clinicName, String state, String city, String area, String buildingNumber, int totalSlots, String openingTime, String closingTime, int serviceDays, int totalOccupiedSlots,String doctorId, String doctorName, String specialization, String doctor_contact, String doctor_mail,List<Appointment> appointment) {
        this.clinicID = clinicID;
        this.clinicName = clinicName;
        this.state = state;
        this.city = city;
        this.area = area;
        this.buildingNumber = buildingNumber;
        this.totalSlots = totalSlots;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.serviceDays = serviceDays;
        this.totalOccupiedSlots=totalOccupiedSlots;
        this.doctorId=doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.doctor_contact = doctor_contact;
        this.doctor_mail=doctor_mail;
        this.appointment = appointment;
    }


}
