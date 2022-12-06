package com.stackroute.appointmentservice.service;


import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import com.stackroute.appointmentservice.repo.PatientRepo;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class PartientServiceTest {

    @Mock
    PatientRepo patientRepo;

    @InjectMocks
    PatientServiceImpl patientService;

    private Patient patient1,patient2;

    @BeforeEach
    public void setUp()
    {
        patient1= new Patient(1,"");
        patient2= new Patient(2,"Sachin Nandanwal");
    }

    @Order(1)
    @Test
    public void addPatient()
    {
        when(patientRepo.save(patient1)).thenReturn(patient1);
        Assertions.assertNotNull(patientService.addPatient(patient1));
    }
    @Order(2)
    @Test
    public void isPatientExistsTest1()
    {
        when(patientRepo.existsById(patient1.getPatientId())).thenReturn(true);
        when(patientService.isPatientExists(patient1)).thenReturn(true);
        Assertions.assertNotNull(patientService.isPatientExists(patient1));
    }

    @Order(3)
    @Test
    public void isPatientExistsTest2()
    {
        when(patientRepo.existsById(patient2.getPatientId())).thenReturn(false);
        when(patientService.isPatientExists(patient2)).thenReturn(false);
        Assertions.assertNotNull(patientService.isPatientExists(patient2));
    }
    @Order(4)
    @Test
    public void getPatientTest1()
    {
        when(patientRepo.getOne(patient1.getPatientId())).thenReturn(patient1);
        when(patientService.getPatient(patient1.getPatientId())).thenReturn(patient1);
        Assertions.assertNotNull(patientService.getPatient(patient1.getPatientId()));
    }

    @Order(5)
    @Test
    public void getPatientTest2()
    {
        when(patientRepo.getOne(patient2.getPatientId())).thenReturn(null);
        when(patientService.getPatient(patient2.getPatientId())).thenReturn(null);
        Assertions.assertNull(patientService.getPatient(patient2.getPatientId()));
    }


}
