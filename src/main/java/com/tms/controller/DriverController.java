package com.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.dto.DriverDTO;
import com.tms.dto.DriverLoginRequest;
import com.tms.dto.LocationDto;
import com.tms.entity.Driver;
import com.tms.entity.Location;
import com.tms.entity.ShipmentHistory;
import com.tms.repository.DriverRepository;
import com.tms.service.DriverService;
import com.tms.service.TrackingService;

@RestController
@RequestMapping("/api/driver")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class DriverController {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
    PasswordEncoder encoder;
	
	@PostMapping("login")
	public String Authenticate(@RequestBody DriverLoginRequest dlr) {
		Driver  d = driverRepository.findByUsername(dlr.getUsername()).orElse(null);
		if(d!=null && encoder.matches(dlr.getPassword(), d.getPassword())) {
			return "Login Successfull!";
		}
		return "Invalid Credentials";
	}
	
	@PostMapping("/register")
	public String saveDriver(@RequestBody DriverDTO driver) {
		if(driverRepository.existsByUsername(driver.getUsername())) return "Username Already Exists";
		driver.setPassword(encoder.encode(driver.getPassword()));
		Driver d = new Driver(driver);
		driverRepository.save(d);
		return "Driver Saved Succesfully";
	}
	
	@GetMapping
	public List<Driver> getAllDrivers(){
		return driverService.getAllDriver();
	}
	
	@GetMapping("/username/{username}")
	public Driver getDriverByUsername(@PathVariable String username){
		return driverRepository.findByUsername(username).orElse(null);
	}
	
	@GetMapping("/shipmenthistory/{id}")
	public List<ShipmentHistory> getShipmentHistory(@PathVariable Long id) {
		return driverService.getShipmentHistory(id);
	}
	
}