package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.repo.clinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {
@Autowired
    private clinicRepository  repository;
    public  String createClinic(ClinicDetail clinicDetail){
        repository.save(clinicDetail);
        return "Clinic registered with id :" +clinicDetail.getClinicID();
    }
    public List<ClinicDetail> getClinicDetail(){
        List<ClinicDetail> clinicDetailList=repository.findAll();
        return clinicDetailList;
    }
}
