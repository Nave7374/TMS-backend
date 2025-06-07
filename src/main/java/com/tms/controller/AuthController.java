package com.tms.controller;

import com.tms.dto.LoginRequest;
import com.tms.dto.SignupRequest;
import com.tms.service.interfaces.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://transportmanagementsys.netlify.app"},allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserAuthenticationService userAuthenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {	
    	return userAuthenticationService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody SignupRequest signUpRequest) {
        return userAuthenticationService.register(signUpRequest);
    }
}
