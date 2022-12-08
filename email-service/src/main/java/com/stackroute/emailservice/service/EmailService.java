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

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

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

}
