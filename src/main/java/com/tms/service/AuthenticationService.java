package com.tms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.tms.dto.LoginRequest;
import com.tms.dto.SignupRequest;
import com.tms.entity.User;
import com.tms.exception.UserIdNotFound;
import com.tms.service.interfaces.UserAuthenticationService;
import com.tms.service.interfaces.UserEntityService;

@Service
public class AuthenticationService implements UserAuthenticationService {

//  @Autowired
//  AuthenticationManager authenticationManager;
	

//  @Autowired
//  JwtTokenProvider tokenProvider;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private UserEntityService userEntityService;
	
	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
		User user = userEntityService.findUserByUsernameOptional(loginRequest.getUsername()).orElseThrow(()-> new UserIdNotFound("Username not found"));
//		System.out.println(loginRequest.getPassword());
//		System.out.println(user.getPassword());
//		System.out.println(encoder.encode(loginRequest.getPassword()));
        if (user.isActive()  && encoder.matches(loginRequest.getPassword(), user.getPassword()) && user.getRole().equals(loginRequest.getRole())) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.badRequest().body("Invalid password");

//      Authentication authentication = authenticationManager.authenticate(
//          new UsernamePasswordAuthenticationToken(
//                  loginRequest.getUsername(),
//                  loginRequest.getPassword()
//          )
//      );
//
//      String jwt = tokenProvider.generateToken(authentication);
//      return jwt;
	}

	@Override
	public ResponseEntity<String> register(SignupRequest signUpRequest) {
		if (userEntityService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
		if(userEntityService.existsByEmail(signUpRequest.getEmail())) throw new UserIdNotFound("Email already taken!");
        signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
        User user = new User(signUpRequest);
        
        userEntityService.saveUser(user);

        return ResponseEntity.ok("User registered successfully!");
	}
}