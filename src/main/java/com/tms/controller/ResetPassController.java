package com.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tms.service.interfaces.PasswordResetEntityService;

import jakarta.mail.MessagingException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/password")
public class ResetPassController {

	@Autowired
	private PasswordResetEntityService entityService;
	
	@PostMapping("/forgot-password/{email}")
	public ResponseEntity<String> postMethodName(@PathVariable String email) throws MessagingException {
		return entityService.sendResetPassLink(email);
	}
	
	@PutMapping("/reset")
	public ResponseEntity<String> resetpassword(@RequestParam String token, @RequestParam String password) {
		return entityService.resetpassword(token,password);
	}
	
}