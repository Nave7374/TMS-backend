package com.tms.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tms.entity.Location;
import com.tms.entity.PasswordResetToken;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.service.interfaces.UserEntityService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {
	
	@Autowired
	private JavaMailSender MailSender;
	
	@Autowired
	private UserEntityService userEntityService;
	
	private final String from ="tms.transport45@gmail.com";
	
	@Async
	public void sendEmailforPassReset(String email,User user)throws MessagingException {
		PasswordResetToken passtoken = user.getToken();
		String token = UUID.randomUUID().toString();
		String name = email.split("@")[0];
		String subject = "Password Reset Link";
		String link = "http://localhost:3000/reset-password?token="+token;
		
		String body = """
				<p>Hello <b>%s</b>,</p>
				<p>We received a request to reset your password.</p>
				<p>
                   <a href="%s" style="background-color:green;color:white;padding:10px 15px;text-decoration:none;border-radius:5px;">
                      Click here to reset your password
                   </a>
                </p>
                <p>If you didn’t request this, you can ignore this email.</p>
                <p>Thanks,<br/>Tms Admin</p>		
				""".formatted(name,link);
		if(passtoken==null) {
			passtoken = new PasswordResetToken();
			passtoken.setUser(user);
		}
		passtoken.setToken(token);
		passtoken.setExpirydate(LocalDateTime.now().plusMinutes(15));
		user.setToken(passtoken);
		userEntityService.saveUser(user);
		
		MimeMessage mail = MailSender.createMimeMessage();
		MimeMessageHelper msg = new MimeMessageHelper(mail,"utf-8");
		msg.setTo(email);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setText(body,true);
		
		
		MailSender.send(mail);
	}
	
	 @Async
	    public void testAsync() {
	        System.out.println("Async started in: " + Thread.currentThread().getName());
	        try { Thread.sleep(3000); } catch (Exception e) {}
	        System.out.println("Async done.");
	    }
	
	@Async
	public void sendEmailforShipment(User user, Shipment shipment) throws MessagingException {
		String to = user.getEmail();
		String name = user.getFirstName() +" " +user.getLastName();
		String subject = "Shipment Confirmation";
		String link = "http://localhost:3000/track";
		String origin = shipment.getOrigin();
		String destination = shipment.getDestination();
		String shipmentid = shipment.getShipmentNumber();
		Date shipmentdate = shipment.getShipmentDate();
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
		String date = sm.format(shipmentdate);
		String body="""
				<p>Hello <b>%s</b>,</p>
					<p>
					    Your shipment from <b>%s</b> to <b>%s</b> on <b>%s</b> has been 
					    <span style="color:green;"><b>Booked Successfully</b></span> 
					    with Shipment ID: <b>%s</b>.
					</p>
					
					<p>
					    <a href="%s" style="background-color:green;color:white;padding:10px 15px;text-decoration:none;border-radius:5px;display:inline-block;">
					        Track Your Shipment
					    </a>
					</p>
				<p>Thanks,<br/>TMS Admin</p>

				""".formatted(name,origin,destination,date,shipmentid,link);
		
		MimeMessage message = MailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body,true);
		
		MailSender.send(message);
		
	}

	@Async
	public void sendEmailforShipmentDelivered(Shipment shipment, User user, Location l) throws MessagingException {
		String to = user.getEmail();
		String name = user.getFirstName() +" " +user.getLastName();
		String subject = "Shipment Delivered";
		String link = "http://localhost:3000/profile";
		String origin = shipment.getOrigin();
		String destination = shipment.getDestination();
		String shipmentid = shipment.getShipmentNumber();
		Date shipmentdate = shipment.getShipmentDate();
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
		String date = sm.format(shipmentdate);
		String body="""
				<p>Hello <b>%s</b>,</p>
					<p>
					    Your shipment from <b>%s</b> to <b>%s</b> on <b>%s</b> has been 
					    <span style="color:green;"><b>Delivered Successfully</b></span>
					    with Shipment ID: <b>%s</b>.
					</p>
					
					<p>
					    <a href="%s" style="background-color:green;color:white;padding:10px 15px;text-decoration:none;border-radius:5px;display:inline-block;">
					        Find You Shipment
					    </a>
					</p>
				<p>Thanks,<br/>TMS Admin</p>

				""".formatted(name,origin,destination,date,shipmentid,link);
		
		MimeMessage message = MailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body,true);
		
		MailSender.send(message);
	}
	
}