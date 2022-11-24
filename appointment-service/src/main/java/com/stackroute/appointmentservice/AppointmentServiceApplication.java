package com.stackroute.appointmentservice;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointmentServiceApplication {

    static Logger logger;

    public static void main(String[] args) {
        SpringApplication.run(AppointmentServiceApplication.class, args);

        logger = Logger.getLogger(AppointmentServiceApplication.class.getSimpleName());
        BasicConfigurator.configure();

        logger.info("Appointment-Service Started!");
    }

}
