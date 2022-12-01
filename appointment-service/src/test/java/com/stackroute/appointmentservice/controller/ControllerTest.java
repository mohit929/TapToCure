package com.stackroute.appointmentservice.controller;

import com.stackroute.appointmentservice.model.Appointment;
import com.stackroute.appointmentservice.model.AppointmentStatus;
import com.stackroute.appointmentservice.model.Patient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {
    @Autowired
    Controller controller;


    @Test
    @Order(1)
    void testHome() {
        Assertions.assertEquals("home", controller.home());
    }

    @Test
    @Order(2)
    void testCreateAppointment() {
        // Test for getAllAppointments: When 0 records are there
        Assertions.assertNull( controller.getAppointment());
        // Test for getAvailableAppointment: When 0 records are there
        Assertions.assertNull(controller.getAvailableAppointment());

        Assertions.assertNotNull(controller.createAppointment(new Appointment(1, "01/11/2022", "01:00", AppointmentStatus.AVAILABLE, null)));
        Assertions.assertNull(controller.createAppointment(new Appointment(1, "01/11/2022", "01:00", AppointmentStatus.AVAILABLE, null)));

        Assertions.assertNotNull(controller.createAppointment(new Appointment(2, "02/11/2022", "02:00", null, null)));
        Assertions.assertNull(controller.createAppointment(new Appointment(2, "02/11/2022", "02:00", null, null)));

        Assertions.assertEquals(AppointmentStatus.AVAILABLE, controller.createAppointment(new Appointment(3, "03/11/2022", "02:00", null, null)).getAppointmentStatus());

        Assertions.assertEquals(1, controller.createAppointment(new Appointment(4, "03/11/2022", "02:00", null, null)).getPatientDetails().getPatientId());

        // Test for getAvailableAppointment: When 0 bookings are there
        Assertions.assertNull(controller.findByPatientDetailsAndAppointmentStatus(2,AppointmentStatus.BOOKED));
    }

    @Test
    @Order(3)
    void testBookAppointment()  {


        Assertions.assertNotNull(controller.bookAppointment(new Appointment(1, new Patient("Sachin Nandanwal"))));
        Assertions.assertNull(controller.bookAppointment(new Appointment(1, new Patient(2))));

        Assertions.assertNotNull(controller.bookAppointment(new Appointment(2, new Patient("Keerti Nandanwal"))));
        Assertions.assertNull(controller.bookAppointment(new Appointment(2, new Patient(3))));

        Assertions.assertNotNull(controller.bookAppointment(new Appointment(3, new Patient(2))));
    }

    @Test
    @Order(4)
    void testUpdateAppointment() {
        Assertions.assertNotNull(controller.updateAppointment(new Appointment(1, new Patient("Chetan Sunhare"))));
        Assertions.assertNotNull(controller.updateAppointment(new Appointment(1, new Patient(4))));
        Assertions.assertNotNull(controller.updateAppointment(new Appointment(1, "22/11/2022", "22:22", null, new Patient(3))));
        Assertions.assertNull(controller.updateAppointment(new Appointment(4, "22/11/2022", "22:22", null, new Patient(4))));
        Assertions.assertNull(controller.updateAppointment(new Appointment(4, "22/11/2022", "04:44", null, new Patient(4))));

        Assertions.assertNotNull(controller.updateAppointment(new Appointment(1, "22/11/2022", "04:44", AppointmentStatus.CANCELLED, new Patient(4))));

    }

    @Order(5)
    @Test
    void testCancelAppointment() {
        Assertions.assertNotNull(controller.cancelAppointment(2));
        Assertions.assertNull(controller.cancelAppointment(2));
        Assertions.assertNotNull(controller.cancelAppointment(3));
        Assertions.assertNull(controller.cancelAppointment(4));
    }

    @Order(6)
    @Test
    void testDeleteAppointment() {
        Assertions.assertNotNull(controller.deleteAppointment(1));
        Assertions.assertNull(controller.deleteAppointment(5));
    }

    @Order(7)
    @Test
    void testGetAllAppointment() {
        Assertions.assertNotEquals(0, controller.getAppointment().size());
    }

    @Order(8)
    @Test
    void testGetAppointment() {
        //Assertions.assertNotNull(controller.getAppointment(1));
        Assertions.assertNotNull(controller.getAppointment(2));
        Assertions.assertNotNull(controller.getAppointment(3));
        Assertions.assertNull(controller.getAppointment(10));
    }

    @Order(9)
    @Test
    void testGetAvailableAppointment() {
        Assertions.assertNotNull(controller.getAvailableAppointment());
    }

    @Order(10)
    @Test
    void testGetAppointmentByPatientId()
    {
        Assertions.assertNotNull(controller.findByPatientDetailsAndAppointmentStatus(2,AppointmentStatus.CANCELLED));
    }

}
