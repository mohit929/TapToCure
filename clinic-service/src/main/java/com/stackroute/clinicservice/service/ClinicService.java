package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.model.DoctorDetail;
import com.stackroute.clinicservice.repo.clinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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
    }
