package com.tms.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.entity.User;

import jakarta.mail.MessagingException;

public interface EmailEntityService {

	void saveUser(User user);

	ResponseEntity<String> sendResetPassLink(String email) throws MessagingException;

	ResponseEntity<?> sendConfirmationMail(User user, Shipment s) throws MessagingException;

	void sendShipmentDeliveredEmail(Shipment s , User user, Location l) throws MessagingException;
	
}