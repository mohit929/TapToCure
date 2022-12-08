package com.stackroute.emailservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EmailServiceTest {

    EmailService emailService = new EmailService();

    @Test
    void testMailSendSuccess() throws Exception {

        String message ="Test Massage";
        String subject = "Test Subject";
        String toMailAddress = "abc@gmail.com";

        boolean result = emailService.sendEmail(message, subject, toMailAddress);
        Assertions.assertTrue(result);

    }

    @Test
    void testMailSendFailure() throws Exception {

        String message ="Test Massage";
        String subject = "Test Subject";
        String toMailAddress = "abc";

        boolean result = emailService.sendEmail(message, subject, toMailAddress);
        Assertions.assertFalse(result);

    }

}
