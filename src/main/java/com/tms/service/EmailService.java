package com.tms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.exception.UserIdNotFound;
import com.tms.service.interfaces.EmailEntityService;
import com.tms.service.interfaces.UserEntityService;

import jakarta.mail.MessagingException;

@Service
public class EmailService implements EmailEntityService{

	@Autowired
	private UserEntityService userEntityService;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public ResponseEntity<String> sendResetPassLink(String email) throws MessagingException {
		boolean flag = userEntityService.existsByEmail(email);
		if(flag) {
			emailSender.sendEmailforPassReset(email,userEntityService.findUserByEmailOptional(email).orElseThrow(()->new UserIdNotFound("User Not Found")));
			return ResponseEntity.ok("Reset Link Was sent to the E-mail : "+ email);
		}
		return ResponseEntity.badRequest().body("Email Not Found");
	}

	@Override
	public void saveUser(User user) {
		userEntityService.saveUser(user);
	}
	
	@Override
	public ResponseEntity<?> sendConfirmationMail(User user,Shipment shipment) throws MessagingException{
		emailSender.sendEmailforShipment(user,shipment);
//		emailSender.testAsync();
		return ResponseEntity.ok("Shipment Booked Succesfully");
	}



}
