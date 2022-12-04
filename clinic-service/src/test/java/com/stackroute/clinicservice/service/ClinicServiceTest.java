package com.stackroute.clinicservice.service;

import com.stackroute.clinicservice.model.Appointment;
import com.stackroute.clinicservice.model.AppointmentStatus;
import com.stackroute.clinicservice.model.ClinicDetail;
import com.stackroute.clinicservice.repo.clinicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicServiceTest {
    private ClinicDetail detail1, detail2;
    private Appointment appointmentDetails1, appointmentDetails2;
    @Autowired
    private ClinicService service;
    @MockBean
    private clinicRepository repository;
    private MockMvc mockMvc;
    //testcase for getclinic
    public List<Appointment> getappointmentlist(){
        List<Appointment> list=new ArrayList<>();
        list.add(new Appointment("1122","22-12-2022","10:00AM",AppointmentStatus.Available));
        list.add(new Appointment("1123","22-12-2022","11:00AM",AppointmentStatus.Available));
        return list;
    }
    @Test
    public void getClinicServiceTest(){
        when(repository.findAll()).thenReturn(
                Stream.of(new ClinicDetail(111,"Lifeline","UP","Bareilly","Nh24","10",100,"7:00AM","10:00PM",44,75,111,"Dr.MK","ortho","9002778201",getappointmentlist())
        ,new ClinicDetail(122,"Lifeline","UP","Bareilly","Nh24","11",100,"7:00AM","10:00PM",44,75,111,"Dr.MK","ortho","9002778201",getappointmentlist())).collect(Collectors.toList()));
        /*Stream.of(new ClinicDetail(111,"Lifeline","UP","Bareilly","Nh24","10",100,"7:00AM","10:00PM",44,75,getappointmentlist()),new ClinicDetail(122,"Lifeline","UP","Bareilly","Nh24","11",100,"7:00AM","10:00PM",44,75,getappointmentlist())).collect(Collectors.toList());*/
       assertEquals(2,service.getClinicDetail().size());
    }
    @Test
    public void getClinicDetailByIdTest(){
        int id=133;
        when(repository.findById(id)).thenReturn(Optional.of(new ClinicDetail(111,"Lifeline","UP","Bareilly","Nh24","10",100,"7:00AM","10:00PM",44,75,111,"Dr.MK","ortho","9002778201",getappointmentlist())));
       /* List<ClinicDetail> clinicDetailOptional=List.of(new ClinicDetail(111,"Lifeline","UP","Bareilly","Nh24","10",100,"7:00AM","10:00PM",44,75,getappointmentlist()));
        when(repository.findById(id)).thenReturn(Stream.of(clinicDetailOptional))).collect(Collectors.toList());*/
        ClinicDetail clinicDetail=new ClinicDetail(111,"Lifeline","UP","Bareilly","Nh24","10",100,"7:00AM","10:00PM",44,75,111,"Dr.MK","ortho","9002778201",getappointmentlist());
                assertEquals(clinicDetail,service.searchClinic(id).get());
    }
    @Test
    public void saveClinicTest(){
        ClinicDetail clinicDetail=new ClinicDetail(111,"Lifeline","UP","Bareilly","Nh24","10",100,"7:00AM","10:00PM",44,75,111,"Dr.MK","ortho","9002778201",getappointmentlist());
        when(repository.save(clinicDetail)).thenReturn(clinicDetail);
        assertEquals("Clinic registered with id :" +clinicDetail.getClinicID(),"Clinic registered with id :" +repository.save(clinicDetail).getClinicID());
    }
    @Test
    public void deleteClinicTest() {
      /*  Mockito.when(service.deleteClinicDetail(1111)).thenReturn("SUCCESS");
        mockMvc.perform(MockMvcRequestBuilders.delete("/getClinicDetail/{clinicId}", 1111))
                .andExpect(status().isOk());*/

        int id = 1111;

        willDoNothing().given(repository).deleteById(id);

        // when -  action or the behaviour that we are going test
        service.deleteClinicDetail(id);

        // then - verify the output
        verify(repository, times(0)).deleteById(id);
    }
        }







