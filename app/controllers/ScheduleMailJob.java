package controllers;

import java.util.Properties;
import javax.mail.*;  
import javax.mail.internet.*;  
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import play.Logger;


public class ScheduleMailJob implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
					
		String to = CronScheduler.to;
		final String from = CronScheduler.from;
		final String password = CronScheduler.password;
		String subject = CronScheduler.subject;
		String message_ = CronScheduler.message;		
		
		//Get the session object  
		Properties props = new Properties();  
		props.put("mail.smtp.host", "smtp.gmail.com");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");  
		   
		Session session = Session.getDefaultInstance(props,  
			new javax.mail.Authenticator() {  
				protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(from,password);  
			}  
		});  
		   
		  //compose message  
		  try {  
			  MimeMessage message = new MimeMessage(session);  
			  message.setFrom(new InternetAddress(to));  
			  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			  message.setSubject(subject);  
			  message.setText(message_);  
		     
			  //send message  
			  Transport.send(message);  
		  	  System.out.println("message sent successfully");  
		   
		  } catch (MessagingException e) {			   
			   Logger.info("javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted");
			 }  				
	}
}
