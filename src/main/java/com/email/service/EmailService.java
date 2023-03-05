package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public Boolean sendEmail(String subject, String message, String to) {

		Boolean f = false;

		String from = "<Email Id>";

		// Validate for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		// Setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object...
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("<Email Id>", "<email password>");
			}
		});

		session.setDebug(true);

		// Step 2 : compose the message [text, multi media]
		MimeMessage m = new MimeMessage(session);

		try {
			// from email
			m.setFrom(from);

			// adding recipiant to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message

			m.setText(message);

			// send

			// step 3 : send the message using Transport class
			Transport.send(m);

			System.out.println("Email send successfully...................");
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
