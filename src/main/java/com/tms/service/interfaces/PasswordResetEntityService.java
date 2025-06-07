package com.tms.service.interfaces;

import org.springframework.http.ResponseEntity;

import jakarta.mail.MessagingException;

public interface PasswordResetEntityService {

	ResponseEntity<String> sendResetPassLink(String email) throws MessagingException;

	ResponseEntity<String> resetpassword(String token, String password);

}