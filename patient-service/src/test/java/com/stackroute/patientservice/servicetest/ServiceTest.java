package com.stackroute.patientservice.servicetest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stackroute.patientservice.exception.*;
import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.repository.PatientRepository;
import com.stackroute.patientservice.service.PatientServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTest {

    @InjectMocks
    private PatientServiceImpl service;

    @Mock
    private PatientRepository repository;

    Patient patient1, patient2, patient3, patient4, patient5;

    @BeforeEach
    public void setUp(){
        patient1 = new Patient("1011","Rohan Singh", "Male", "A+",
                "26/12/1997","8695863259","rohan234@gmail.com",
                "Kannauj", "UP","208801","HeadAche, fever");

        patient2 = new Patient("1001","Rohan Singh", "Male", "A+",
                "26/12/1997","8695863259","rohan@gmail.com",
                "Kannauj", "UP","208801","HeadAche, fever");
        patient3 = new Patient("1021","Rohan Singh", "Male", "A+",
                "26/12/1997","8695863259","rohan@gmail.com",
                "Kannauj", "UP","208801","HeadAche, fever");
        patient4 = new Patient("1011","Rohan Singh", "Male", "AB+",
                "26/12/1997","8695863259","rohan234@gmail.com",
                "Kanpur", "UP","208801","HeadAche, fever");

        patient5 = new Patient("1081","Rohan Singh", "Male", "AB+",
                "26/12/1997","8695863259","rohan234@gmail.com",
                "Kanpur", "UP","208801","HeadAche, fever");
    }

    @AfterEach
    public void setNull(){
        patient1 = null;
        patient2 = null;
        patient3 = null;
        patient4 = null;
        patient5 = null;
    }


    @Test
    public void testRegisterPatient() throws Exception {
        when(repository.findById(patient1.getPatientId())).thenReturn(Optional.empty());
        when(repository.insert(patient1)).thenReturn(patient1);
        Assertions.assertEquals("PatientDTO registered successfully with id : 1011",service.registerPatient(patient1));
        verify(repository,times(1)).findById(any());
        verify(repository,times(1)).insert((Patient) any());
    }

    @Test
    public void testRegisterPatientException(){
        when(repository.findById(patient1.getPatientId())).thenReturn(Optional.of(patient1));
        when(repository.insert(patient1)).thenThrow(PatientAlreadyExistsException.class);
        Assertions.assertThrows(PatientAlreadyExistsException.class,() -> service.registerPatient(patient1));
        verify(repository,times(1)).findById(any());
    }

    @Test
    public void testGetPatientDetails() throws PatientNotFoundException {
        when(repository.findById(patient2.getPatientId())).thenReturn(Optional.of(patient2));
        Assertions.assertEquals(patient2.toString(),service.getPatientDetails(patient2.getPatientId()));
        verify(repository,times(1)).findById(any());
    }
    @Test
    public void testGetPatientDetailsException(){
        when(repository.findById(patient2.getPatientId())).thenThrow(NoSuchElementException.class);
        Assertions.assertThrows(PatientNotFoundException.class, () -> service.getPatientDetails(patient2.getPatientId()));
        verify(repository,times(1)).findById(any());
    }

    @Test
    public void testGetAllPatientDetails() throws NoPatientRecordsPresentException {
        when(repository.findAll()).thenReturn(List.of(patient1,patient2,patient3,patient4,patient5));
        Assertions.assertEquals(List.of(patient1,patient2,patient3,patient4,patient5),service.getAllPatientDetails());
        verify(repository,times(2)).findAll();
    }

    @Test
    public void testGetAllPatientDetailsException(){
        when(repository.findAll()).thenReturn(List.of());
        Assertions.assertThrows(NoPatientRecordsPresentException.class, ()->service.getAllPatientDetails());
        verify(repository,times(1)).findAll();
    }

    @Test
    public void testUpdatePatientDetails() throws Exception {
        when(repository.findById(patient1.getPatientId())).thenReturn(Optional.of(patient1));
        when(repository.save(patient1)).thenReturn(patient1);
        Assertions.assertEquals(patient1.toString(),service.updatePatientDetails(patient1));
        verify(repository,times(1)).findById(any());
        verify(repository,times(1)).save(any());
    }
    @Test
    public void testUpdatePatientDetailsException(){
        when(repository.findById(patient5.getPatientId())).thenReturn(Optional.empty());
        Assertions.assertThrows(PatientNotFoundException.class,() -> service.updatePatientDetails(patient5));
        verify(repository,times(1)).findById(any());
    }
    @Test
    public void testIsEmailExists(){
        when(repository.findAll()).thenReturn(List.of(patient1,patient2,patient3,patient4,patient5));
        Assertions.assertThrows(EmailAlreadyExistsException.class,() -> service.isEmailExists(patient5.getPatientEmail()));
        verify(repository,times(1)).findAll();
    }

    @Test
    public void testDeletePatient() throws PatientNotFoundException {
        when(repository.findById(patient1.getPatientId())).thenReturn(Optional.of(patient1));
        String message = service.deletePatient(patient1.getPatientId());
        Assertions.assertEquals("PatientDTO record deleted successfully having id : 1011",message);
        verify(repository,times(1)).findById(any());
        verify(repository,times(1)).deleteById(patient1.getPatientId());
    }
}
