package com.stackroute.appointmentservice;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AppointmentServiceApplication {
    static final Logger logger = Logger.getLogger(AppointmentServiceApplication.class.getSimpleName());

    public static void main(String[] args) {
        SpringApplication.run(AppointmentServiceApplication.class, args);
        BasicConfigurator.configure();
        logger.info("Appointment-Service Started!");
    }

}
