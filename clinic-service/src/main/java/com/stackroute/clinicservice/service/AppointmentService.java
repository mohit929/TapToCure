package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.repo.appointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private appointmentRepository arepository;
    public  String createAppointment(Appointment appointment){
        arepository.save(appointment);
        return "Appointment registered with id :" +appointment.getAppointment_id();
    }
    public List<Appointment> getAppointment(){
        List<Appointment> appointmentList=arepository.findAll();
        return appointmentList;
    }

}
