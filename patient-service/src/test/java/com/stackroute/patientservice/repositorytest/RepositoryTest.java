package com.stackroute.patientservice.repositorytest;

import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.repository.PatientRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTest {


    @Mock
    private PatientRepository repository;

    Patient patient1,patient2,patient3,patient4,patient5;

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
    public void testSave(){
        when(repository.insert(patient4)).thenReturn(patient4);
        Assertions.assertEquals(patient4,repository.insert(patient4));
    }

    @Test
    public void testGetPatient(){
        when(repository.findById(patient5.getPatientId())).thenReturn(Optional.of(patient5));
        Assertions.assertEquals(Optional.of(patient5),repository.findById(patient5.getPatientId()));
    }

    @Test
    public void testGetAllPatient(){
        when(repository.findAll()).thenReturn(List.of(patient1,patient2,patient3,patient4,patient5));
        Assertions.assertEquals(List.of(patient1,patient2,patient3,patient4,patient5),repository.findAll());
    }
    @Test
    public void testUpdatePatient(){
        when(repository.save(patient1)).thenReturn(patient1);
        Assertions.assertEquals(patient1,repository.save(patient1));
    }
}
