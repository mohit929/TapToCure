package com.stackroute.appointmentservice.service;

import com.stackroute.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.appointmentservice.exception.AppointmentNotExistsException;
import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.AppointmentRepo;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {


    @Mock
    PatientRepo patientRepo;

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    AppointmentRepo appointmentRepo;
    @InjectMocks
    AppointmentServiceImpl appointmentService;

    private Patient patient;
    private Appointment appointment;
    private List<Appointment> appointments = new ArrayList<Appointment>();

    @BeforeEach
    public void setUp() {
        patient = new Patient(1, "");
        appointment = new Appointment(1, "01/12/2022", "01:00", AppointmentStatus.AVAILABLE, patient);
    }

    @Order(1)
    @Test
    public void getAppointmentTest()
    {
        when(appointmentRepo.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(AppointmentNotExistsException.class,()->appointmentService.getAppointment());
    }

    @Order(2)
    @Test
    public void createAppointmentTest1() throws AppointmentAlreadyExistsException, CloneNotSupportedException {
        when(appointmentRepo.save(appointment)).thenReturn(appointment);
        Assertions.assertNotNull(appointmentService.createAppointment(appointment));
    }

    @Order(3)
    @Test
    public void createAppointmentTest2() throws CloneNotSupportedException {
        try {
            when(appointmentRepo.existsById(appointment.getAppointmentId())).thenThrow(AppointmentAlreadyExistsException.class);
        } catch (Exception e) {

        }
    }

}
