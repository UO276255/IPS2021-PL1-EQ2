package com.uniovi.muebleria.maven.util;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

public class SendMail {

	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      /*msg.setFrom(new InternetAddress("uo276900@uniovi.es", "Reply-To"));

	      //msg.setReplyTo(InternetAddress.parse("uo276900@uniovi.es", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");
		  */
	      msg.setFrom(new InternetAddress("uo276900@uniovi.es"));
	      msg.setSubject(subject);
	      msg.setText(body);
	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	      System.out.println(body);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public static void sendMailWithoutAuth(String toEmail, String subject, String body) {
		
	    System.out.println("SimpleEmail Start");
		
	    String smtpHostServer = "smtp.gmail.com";
	    String emailID = toEmail;
	    
	    Properties props = System.getProperties();

	    props.put("mail.smtp.host", smtpHostServer);

	    Session session = Session.getInstance(props, null);
	    
	    sendEmail(session, emailID,subject, body);
	}
	
	public static void sendMailWithTTLS(String toEmail, String subject, String body) {
		final String fromEmail = "adrian.estrada2001@gmail.com"; //requires valid gmail id
		final String password = "Adrian2001"; // correct password for gmail id
		final String emailID = toEmail; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		sendEmail(session, emailID,subject, body);
		
	}

	public static void sendMailWithSSL(String toEmail, String subject, String body) {
		final String fromEmail = "adrian.estrada2001@gmail.com"; //requires valid gmail id
		final String password = "Adrian2001"; // correct password for gmail id
		final String emailID = toEmail; // can be any email id 
		
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
	        sendEmail(session, emailID,subject, body);
	}
}
