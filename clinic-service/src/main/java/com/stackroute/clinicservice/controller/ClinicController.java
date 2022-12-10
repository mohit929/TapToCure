package com.stackroute.clinicservice.controller;

import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.model.Response;
import com.stackroute.clinicservice.publisher.RabbitmqPublisher;
import com.stackroute.clinicservice.rabbitmqResponseDTO.UserDTO;
import com.stackroute.clinicservice.repo.DoctorRepo;
import com.stackroute.clinicservice.repo.clinicRepository;
import com.stackroute.clinicservice.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/clinic-service")
public class ClinicController {

        @Autowired
        private ClinicService clinicService;
        @Autowired
        private RabbitmqPublisher rabbitmqPublisher;
        @Autowired
        private clinicRepository repository;
        @Autowired
        private DoctorRepo doctorRepo;
        @PostMapping("/saveclinic")
        public Response saveClinic(@RequestBody ClinicDetail clinicDetail){
                     clinicService.createClinic(clinicDetail);
                     rabbitmqPublisher.send(clinicDetail);
                     return new Response(clinicDetail.getClinicID()+"inserted", Boolean.TRUE);


        }
        @GetMapping("/getClinicDetail")
        public Response getClinicDetail(){
            List<ClinicDetail> clinicDetailList=repository.findAll();

                   /*clinicService.getClinicDetail();*/
                   return new Response("clinic counts:"+ clinicDetailList.size(),Boolean.TRUE);


        }
        @GetMapping("/getClinicDetail/{clinicId}")
        public Optional<ClinicDetail> searchClinic(@PathVariable int clinicId) {

                 return clinicService.searchClinic(clinicId);


        }
        @DeleteMapping("/getClinicDetail/{clinicId}")
        public String deleteClinicDetail(@PathVariable int clinicId)
        {
               try {
                       return clinicService.deleteClinicDetail(clinicId);
               }
               catch (Exception e){
                       e.printStackTrace();
               }

               return null;
        }
        @PutMapping("/updateClinicAppointment/{clinicId}")
    public ClinicDetail updateClinicAppointment(@RequestBody List<Appointment> appointmentList, @PathVariable int clinicId) {
           return clinicService.updateClinicAppointment(appointmentList,clinicId);


        }



}

