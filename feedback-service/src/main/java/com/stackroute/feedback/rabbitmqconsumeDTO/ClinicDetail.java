package com.stackroute.feedback.rabbitmqconsumeDTO;

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
	public int getClinicID() {
		return clinicID;
	}
	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public int getTotalSlots() {
		return totalSlots;
	}
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public int getServiceDays() {
		return serviceDays;
	}
	public void setServiceDays(int serviceDays) {
		this.serviceDays = serviceDays;
	}
	public static int getTotalOccupiedSlots() {
		return totalOccupiedSlots;
	}
	public static void setTotalOccupiedSlots(int totalOccupiedSlots) {
		ClinicDetail.totalOccupiedSlots = totalOccupiedSlots;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getDoctor_contact() {
		return doctor_contact;
	}
	public void setDoctor_contact(String doctor_contact) {
		this.doctor_contact = doctor_contact;
	}
	public String getDoctor_mail() {
		return doctor_mail;
	}
	public void setDoctor_mail(String doctor_mail) {
		this.doctor_mail = doctor_mail;
	}
	public List<Appointment> getAppointment() {
		return appointment;
	}
	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}


}
