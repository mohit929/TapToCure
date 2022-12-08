package com.stackroute.patientservice.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.patientservice.controller.PatientController;
import com.stackroute.patientservice.exception.PatientAlreadyExistsException;
import com.stackroute.patientservice.model.Patient;
import com.stackroute.patientservice.service.PatientService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


import static org.mockito.Mockito.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    PatientController controller;

    @Mock
    PatientService service;

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

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
    public void testHome(){
        Assertions.assertEquals("home",controller.home());
    }

    @Test
    public void testRegisterPatient() throws Exception {
        when(service.registerPatient(any())).thenReturn("PatientDTO registered successfully with id : 1011");
        mockMvc.perform(post("/patientService/registerPatient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(patient1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service,times(1)).registerPatient(any());
    }

    @Test
    public void testRegisterPatientException() throws Exception {
        when(service.registerPatient(patient1)).thenThrow(PatientAlreadyExistsException.class);
        mockMvc.perform(post("/patientService/registerPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(patient1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service,times(1)).registerPatient(any());

    }

    @Test
    public void testGetPatientDetails() throws Exception {
        when(service.getPatientDetails(patient1.getPatientId())).thenReturn(String.valueOf(patient1));
        mockMvc.perform(get("/patientService/getPatientDetails/1011")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service,times(1)).getPatientDetails(any());
    }

    @Test
    public void testGetAllPatientDetails() throws Exception {
        when(service.getAllPatientDetails()).thenReturn(List.of(patient1,patient2,patient3,patient4,patient5));
        mockMvc.perform(get("/patientService/getAllPatientDetails")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(service,times(1)).getAllPatientDetails();
    }

    @Test
    public void testUpdatePatientDetails() throws Exception {
        when(service.updatePatientDetails(patient1)).thenReturn(String.valueOf(patient1));
        mockMvc.perform(post("/patientService/updatePatientDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(patient1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service,times(1)).updatePatientDetails(any());
    }

    @Test
    public void testRemovePatient() throws Exception {
        when(service.deletePatient(patient1.getPatientId())).thenReturn("PatientDTO record deleted successfully having id : 1011");
        mockMvc.perform(delete("/patientService/deletePatient/1011")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service,times(1)).deletePatient(any());
    }

    private static String jsonToString(final Object o) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(o);
            result = jsonContent;
            return result;

        } catch (JsonProcessingException e) {
            result = "JsonProcessingException";
        }
        return result;
    }
}
