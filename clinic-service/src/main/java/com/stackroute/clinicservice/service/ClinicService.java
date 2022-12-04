package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.repo.clinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {
    @Autowired
    private clinicRepository  repository;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ClinicService clinicService;
    public  String createClinic(ClinicDetail clinicDetail){
        for (Appointment appointment :clinicDetail.getAppointment()
                ) {
            appointmentService.createAppointment(appointment);

        }
      //  appointmentService.createAppointment(clinicDetail.getAppointment());
        repository.save(clinicDetail);
        return "Clinic registered with id :" +clinicDetail.getClinicID();
    }
    public List<ClinicDetail> getClinicDetail(){
        List<ClinicDetail> clinicDetailList=repository.findAll();
        System.out.println("Getting data from Database :" + clinicDetailList);
        return clinicDetailList;
    }
    public Optional<ClinicDetail> searchClinic(int clinicId){
        Optional<ClinicDetail> clinicDetailOptional = repository.findById(clinicId);
        return clinicDetailOptional;
    }
    public String deleteClinicDetail(int clinicId) {
        /* Optional<ClinicDetail> clinicdetails = searchClinic(clinicId);*/
        if (repository.existsById(clinicId)) {
            repository.deleteById(clinicId);
            return "Deleted Clinic Record";
        }
        return "Clinic Record not found";
    }
 /*   public ClinicDetail updateClinicAppointment(Appointment modifiedAppointment){
        if(clinicService.isClinicExists())*/


}