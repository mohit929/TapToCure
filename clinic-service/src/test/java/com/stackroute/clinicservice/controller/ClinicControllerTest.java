package com.stackroute.clinicservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.clinicservice.model.ClinicDetail;

import com.stackroute.clinicservice.model.Response;
import com.stackroute.clinicservice.service.ClinicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    private ClinicService service;
    ObjectMapper om=new ObjectMapper();
    @Before
    public void setUp(){
     mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void saveClinicTest() throws Exception {
        ClinicDetail clinicDetail=new ClinicDetail(111, "Lifeline", "UP", "Bareilly", "Nh24", "10", 100, "7:00AM", "10:00PM", 7, 75,"111","Dr.MK","ortho","9002778201","abc@gmail.com",null);
        clinicDetail.setClinicID(2334);
        clinicDetail.setClinicName("Medicity");
        String jsonRequest= om.writeValueAsString(clinicDetail);
        MvcResult result=mockMvc.perform(post("/saveclinic").content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent=result.getResponse().getContentAsString();
        Response response=om.readValue(resultContent,Response.class);
        Assert.assertTrue(response.isStatus()==Boolean.TRUE);
    }
    @Test
    public void getClinicTest() throws Exception {

        MvcResult result=mockMvc.perform(get("/getClinicDetail").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent=result.getResponse().getContentAsString();
        Response response=om.readValue(resultContent,Response.class);
        Assert.assertTrue(response.isStatus()==Boolean.TRUE);
    }
   /* @Test
    public void getClinicTestById() throws Exception{
        ClinicDetail clinicDetail=new ClinicDetail();
        clinicDetail.setClinicName("Vninayak Hospital");
        clinicDetail.setCity("Bareilly");
        clinicDetail.setArea("D.D Puram");
        when(service.searchClinic(anyInt())).thenReturn(Optional.of(clinicDetail));
        mockMvc.perform(MockMvcRequestBuilders.get("/getClinicDetail/12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clinicName").value("Vinayak Hospital"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Bareilly"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.area").value("D.D Puram"))
                .andExpect(status().isOk());
        *//*mockMvc.perform( MockMvcRequestBuilders
                        .get("/getClinicDetail/{clinicId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                       .andExpect(MockMvcResultMatchers.jsonPath("$.clinicID").value(111));*//*
    }*/
}
