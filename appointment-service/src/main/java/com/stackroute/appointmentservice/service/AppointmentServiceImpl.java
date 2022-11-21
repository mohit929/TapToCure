package com.stackroute.appointmentservice.service;


import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.repo.AppointmentRepo;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    PatientRepo patientRapo;

    @Override
    public Appointment bookAppointment(Appointment appointment) {
        if (!appointmentRepo.existsById(appointment.getAppointmentId())) {
            patientRapo.save(appointment.getPatientDetails());
            System.out.println("Patient Inserted");
            appointmentRepo.save(appointment);
            System.out.println("Appointment Booked");
            return appointment;
        }
        return null;
    }

    public Appointment updateAppointment(Appointment appointment) {
        if (appointmentRepo.existsById(appointment.getAppointmentId())) {
            patientRapo.save(appointment.getPatientDetails());
            System.out.println("Patient Updated");
            appointmentRepo.save(appointment);
            System.out.println("Appointment Updated");


            return appointment;
        }

        return null;
    }

    @Override
    public Optional<Appointment> deleteAppointment(int appointmentId) {
        Optional<Appointment> appointment = searchAppointment(appointmentId);

        if (appointment != null) {
            appointmentRepo.deleteById(appointmentId);
        }

        return appointment;
    }

    public Optional<Appointment> searchAppointment(int appointmentId) {
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        return appointment;
    }


}
