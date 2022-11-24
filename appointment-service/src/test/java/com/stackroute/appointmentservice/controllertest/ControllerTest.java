package com.stackroute.appointmentservice.controllertest;

import com.stackroute.appointmentservice.controller.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControllerTest
{
    @Autowired
    Controller controller;

    @Test
    void testHome()
    {
        Assertions.assertEquals("home", controller.home());
    }

    @Test
    void testGetAppointmentById()
    {
        Assertions.assertNull(controller.getAppointment(10));
        Assertions.assertEquals( 11,controller.getAppointment(11).getAppointmentId());
        Assertions.assertEquals( 12,controller.getAppointment(12).getAppointmentId());
    }

    @Test
    void testGetAllAppointment()
    {
        Assertions.assertNotNull(controller.getAppointment());
        Assertions.assertNull(controller.getAppointment());
    }
}
