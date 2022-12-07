package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.rabbitmqResponseDTO.UserDTO;
import com.stackroute.clinicservice.repo.AppointmentRepo;
import com.stackroute.clinicservice.repo.clinicRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {
    @Autowired
    private clinicRepository repository;
    @Autowired
    private AppointmentRepo appointmentRepo;


    @Autowired
    private RabbitTemplate template;
    /*private ClinicDetail clinicDetail;*/
    private UserDTO userDTO1;
    @RabbitListener(queues = "Doctor_Q")
    public void cosumeMessageFromQueue(UserDTO userDTO) {


        System.out.println("Message has received from queue with order status: " + userDTO);
        userDTO1=userDTO;
    }

    public String createClinic(ClinicDetail clinicDetail) {
        /*for (Appointment appointment : clinicDetail.getAppointment()
        ) {
            appointmentService.createAppointment(appointment);

        }*/
        clinicDetail.setDoctorId(userDTO1.getUserId());
        clinicDetail.setDoctor_mail(userDTO1.getEmailId());
        clinicDetail.setDoctor_contact(userDTO1.getPhoneNo());
        repository.save(clinicDetail);
        return "Clinic registered with id :" + clinicDetail.getClinicID();
    }

    public List<ClinicDetail> getClinicDetail() {
        List<ClinicDetail> clinicDetailList = repository.findAll();
        System.out.println("Getting data from Database :" + clinicDetailList);
        return clinicDetailList;
    }

    public Optional<ClinicDetail> searchClinic(int clinicId) {
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

    public ClinicDetail updateClinicAppointment(List<Appointment> appointmentList, int clinicId) {
        Optional<ClinicDetail> clinicDetailOptional = repository.findById(clinicId);
        if (clinicDetailOptional.isPresent()) {

            ClinicDetail clinicDetail = clinicDetailOptional.get();
            List<Appointment> existingAppointmentList = clinicDetail.getAppointment();
            for (Appointment appointment : appointmentList) {
                appointmentRepo.save(appointment);
                existingAppointmentList.add(appointment);
            }
            clinicDetail.setAppointment(existingAppointmentList);
            clinicDetail.setTotalSlots(existingAppointmentList.size());
            repository.save(clinicDetail);
            return clinicDetail;

        }
        return null;

    }
}





