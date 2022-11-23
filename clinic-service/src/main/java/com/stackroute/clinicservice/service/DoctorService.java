package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.DoctorDetail;
import com.stackroute.clinicservice.repo.doctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private doctorRepository repository;
    public  String createDoctor(DoctorDetail doctorDetail){
        repository.save(doctorDetail);
        return "Doctor registered with id :" +doctorDetail.getDoctorId();
    }
    public List<DoctorDetail> getDoctorDetail(){
        List<DoctorDetail> doctorDetailList=repository.findAll();
        return doctorDetailList;
    }
    public Optional<DoctorDetail> findById(Integer id)
    {
        return repository.findById(id);
    }
    public Optional<DoctorDetail> searchDoctor(int doctorId){
        Optional<DoctorDetail> doctorDetailOptional = repository.findById(doctorId);
        return doctorDetailOptional;
    }

  /*  public Optional<DoctorDetail> getDoctorByName(String name) {
        Optional<DoctorDetail> doctorDetailList1 = repository.findByName(name);


   return doctorDetailList1;
    }*/
}
