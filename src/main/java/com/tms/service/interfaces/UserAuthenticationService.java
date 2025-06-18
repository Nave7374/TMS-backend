package com.tms.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.tms.dto.LoginRequest;
import com.tms.dto.SignupRequest;

public interface UserAuthenticationService {

	ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

	ResponseEntity<String> register(SignupRequest signUpRequest);

}