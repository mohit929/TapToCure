package com.stackroute.registrationservice.service;

import com.stackroute.registrationservice.repo.UserRepo;
import com.stackroute.registrationservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationServiceImp implements RegistrationServices{


    @Autowired
    private UserRepo repository;

    public String createUser(User user) {

        boolean result=validate(user);
        if(result)
        {
            repository.save(user);
            System.out.println("user created with id : " +user.getUserId());
            return "user created with id : " +user.getUserId();
        }
        else{
            System.out.println("user not created with id : " +user.getUserId());
            return "Password is weak, user not created" ;

        }


    }

    public boolean validate(User user) {

        boolean result=false;
        if(user.getPassword().equals(user.getConfirm_password()))
        {
            Pattern p=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
            Matcher m =p.matcher(user.getPassword());

            result=m.matches();


        }
       return result;

    }

    public List<User> getUser() {
        List<User> userList = repository.findAll();
        return userList;
    }

    public User getByemailId(String email_id) throws RuntimeException{
        try {
            return repository.findByEmailId(email_id).get(0);
        }
        catch (Exception e)
        {
            return null;
        }

    }
    public static void sendEmail(String message, String subject, String to) {

        String from="poojamiskin9@gmail.com";

        // Variable for gmail
        String host = "smtp.gmail.com";

        // get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        // setting important information to properties object

        // host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("taptocures@gmail.com", "qvpbyqilkqqrnrrt");
            }

        });

        session.setDebug(true);

        // Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            // from email
            m.setFrom(from);

            // adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // adding subject to message
            m.setSubject(subject);

            // adding text to message
            m.setText(message);

            // send

            // Step 3 : send the message using Transport class
            Transport.send(m);

            System.out.println("Sent success...................");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int sendOtp(String emailId) {
        Random random = new Random();
        int otp= random.nextInt(99999);
        String message="Your One Time Password (OTP) is "+otp+".";
        String subject="OTP from Tap To Cure";
        sendEmail(message,subject, emailId);
        System.out.println(message);
        return otp;

    }
}
