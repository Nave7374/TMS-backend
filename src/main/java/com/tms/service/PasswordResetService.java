package com.tms.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tms.entity.PasswordResetToken;
import com.tms.entity.User;
import com.tms.exception.ResourceNotFoundException;
import com.tms.repository.PasswordResetRepository;
import com.tms.service.interfaces.EmailEntityService;
import com.tms.service.interfaces.PasswordResetEntityService;

import jakarta.mail.MessagingException;

@Service
public class PasswordResetService implements PasswordResetEntityService {

	@Autowired
	private EmailEntityService emailService;
	
	@Autowired
	private PasswordResetRepository passwordResetRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public ResponseEntity<String> sendResetPassLink(String email) throws MessagingException {
		return emailService.sendResetPassLink(email);
	}

	@Override
	public ResponseEntity<String> resetpassword(String token, String password) {
		PasswordResetToken passreset = passwordResetRepository.findByToken(token).orElseThrow(()-> new ResourceNotFoundException("Token not found"));
		if(passreset.getExpirydate().isBefore(LocalDateTime.now())) throw new RuntimeException("Token Expired");
		User user = passreset.getUser();
		user.setPassword(encoder.encode(password));
		user.setToken(null);
		passreset.setUser(null);
		emailService.saveUser(user);
		passwordResetRepository.delete(passreset); 
		return ResponseEntity.ok("Password Updated");
	}

}
