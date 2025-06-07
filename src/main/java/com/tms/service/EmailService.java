package com.tms.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tms.entity.PasswordResetToken;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.exception.ResourceNotFoundException;
import com.tms.service.interfaces.EmailEntityService;
import com.tms.service.interfaces.UserEntityService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailEntityService{

	@Autowired
	private UserEntityService userEntityService;
	
	@Autowired
	private JavaMailSender MailSender;
	
	private String from ="nave7374@gmail.com";
	
	@Override
	public ResponseEntity<String> sendResetPassLink(String email) throws MessagingException {
		boolean flag = userEntityService.existsByEmail(email);
		if(flag) {
			sendEmail(email,userEntityService.findUserByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found")));
			return ResponseEntity.ok("Reset Link Was sent to the E-mail : "+ email);
		}
		return ResponseEntity.badRequest().body("Email Not Found");
	}

	private void sendEmail(String email,User user)throws MessagingException {
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

	@Override
	public void saveUser(User user) {
		userEntityService.saveUser(user);
	}
	
	@Override
	public ResponseEntity<?> sendConfirmationMail(User user,Shipment shipment) throws MessagingException{
		return sendEmail(user,shipment);
	}

	private ResponseEntity<?> sendEmail(User user, Shipment shipment) throws MessagingException {
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
		
		return ResponseEntity.ok("Shipment has been booked");
	}

}
