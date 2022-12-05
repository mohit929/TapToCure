package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.Clinic;
import com.stackroute.appointmentservice.rabbitpublisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService{

    @Autowired
    Publisher publisher;
    @Override
    public Clinic createDummyClinicAppointment() {
        Appointment a1 = new Appointment(11, "01/11/2023", "01:00",null,null);
        Appointment a2 = new Appointment(12, "02/11/2023", "02:00",null,null);
        Clinic clinic = new Clinic("1", List.of(a1, a2));
        publisher.sendClinic(clinic);
        return clinic;
    }

}
