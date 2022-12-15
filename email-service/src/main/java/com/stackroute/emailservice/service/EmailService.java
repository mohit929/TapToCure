//package com.stackroute.emailservice.service;
//
//import org.springframework.stereotype.Service;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//@Service
//public class EmailService {
//
//	public boolean sendEmail(String message, String subject, String toMailAddress) throws Exception {
//
//		String from="vikasmonu1490@gmail.com";
//		boolean messageDelivered = false;
//		// Variable for gmail
//		String host = "smtp.gmail.com";
//
//		if(!toMailAddress.contains("@") && !toMailAddress.contains(".com")){
//			return false;
//		}
//
//		// get the system properties
//		Properties properties = System.getProperties();
//		System.out.println("PROPERTIES " + properties);
//
//		// setting important information to properties object
//
//		// host set
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//
//		// Step 1: to get the session object..
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("taptocures@gmail.com", "qvpbyqilkqqrnrrt");
//			}
//
//		});
//
//		session.setDebug(true);
//
//		// Step 2 : compose the message [text,multi media]
//		MimeMessage m = new MimeMessage(session);
//
//		try {
//
//			// from email
//			m.setFrom(from);
//
//			// adding recipient to message
//			m.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailAddress));
//
//			// adding subject to message
//			m.setSubject(subject);
//
//			// adding text to message
//			m.setText(message);
//
//			// send
//
//			// Step 3 : send the message using Transport class
//			Transport.send(m);
//
//
//			System.out.println("Sent success...................");
//			messageDelivered =true;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return messageDelivered;
//
//	}
//
//
//}
package com.stackroute.emailservice.service;

import com.stackroute.emailservice.rabbitmq.dto.AppointmentDto;
import com.stackroute.emailservice.rabbitmq.dto.EmailDTO;
import com.stackroute.emailservice.rabbitmq.dto.OtpDto;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
  private String welcomeEmail=null;
	public boolean sendEmail(String message, String subject, String toMailAddress) {

		String from="vikasmonu1490@gmail.com";
		boolean messageDelivered = false;
		// Variable for gmail
		String host = "smtp.gmail.com";

		if(!toMailAddress.contains("@") && !toMailAddress.contains(".com")){
			return false;
		}

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
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailAddress));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step 3 : send the message using Transport class
			Transport.send(m);


			System.out.println("Sent success...................");
			messageDelivered =true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageDelivered;

	}
	public void consumeEmailDto(EmailDTO emailDTO) {
		welcomeEmail = emailDTO.getEmailId();

		String subject="Welcome To TapToCure";
		String message = "Hi "+emailDTO.getUserName()+"!\n\n" +
				"Welcome to the TapToCure!!\n" +
				"We are glad that you are reading this email. We will be happy to help you .\n" +
				"As a thank you for joining us, We would like to give you best service. \n" +
				"\n" +
				"\n" +
				"Thanks and Regards,\n" +
				"Team TapToCure";
		sendEmail(message,subject,welcomeEmail);
	}
	public void consumeOtpDto(OtpDto otpDto){
		String emailId=otpDto.getEmailId();
		String subject="OTP from TapToCure";
		String message="Dear TapToCure user,\n\n" +
				       "Kindly find your otpNo: "+otpDto.getOtpno()+""+
						"\n" +
						"\n" +
						"Thanks and Regards,\n" +
						"Team TapToCure";
		sendEmail(message,subject,emailId);
	}
	public void appintmentDto(AppointmentDto appointment){
		String subject ="Appointment Details";
		String message ="Dear "+appointment.getPatientDetails().getPatientName()+",\n\n"
				+"Kindly find your appointment detail below :\n"
				+"Appointment Id: "+appointment.getAppointmentId()+"\n"
				+"Date: "+appointment.getAppointmentDate()+"\n"
				+"Time: "+appointment.getAppointmentTime()+"\n"
				;
		String email=appointment.getPatientDetails().getPatientEmail();
		sendEmail(message,subject,email);
	}
}
