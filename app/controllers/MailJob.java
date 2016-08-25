package controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import controllers.Command;
import play.Logger;


public class MailJob implements Command {

	public static final String FROM = "from";
	public static final String TO = "to";
	public static final String MSG = "message";
	public static final String SUB = "subject";
	public static final String PASSWORD = "password";
	public static final String IS_MAIL_JOB = "isMailJob";

	public final String from;
	public final String password;
	public final String subject;
	public final String to;
	public final String message;
	public static Properties props = new Properties();

	static {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}

	public MailJob(String from, String password, String subject, String to, String message) {
		this.from = from;
		this.password = password;
		this.subject = subject;
		this.to = to;
		this.message = message;
	}
		
	public void execute() {
		// Get the session object		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// compose message
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(to));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);

			// send message
			Transport.send(mimeMessage);
			System.out.println("message sent successfully");

		} catch (MessagingException e) {
			Logger.info("javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted");
		}
	}

}
