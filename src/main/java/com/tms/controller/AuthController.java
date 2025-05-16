package com.tms.controller;

import com.tms.dto.LoginRequest;
import com.tms.dto.SignupRequest;
import com.tms.entity.User;
import com.tms.repository.UserRepository;
//import com.tms.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    
    @Autowired
    PasswordEncoder encoder;

//    @Autowired
//    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {

    	User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);
        if (user != null && encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    	
//        Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                    loginRequest.getUsername(),
//                    loginRequest.getPassword()
//            )
//        );
//
//        String jwt = tokenProvider.generateToken(authentication);
//        return jwt;
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return "Error: Username is already taken!";
        }
        signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
        User user = new User(signUpRequest);
        
        userRepository.save(user);

        return "User registered successfully!";
    }
}
