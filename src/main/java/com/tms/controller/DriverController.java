package com.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.DAO.DriverDao;
import com.tms.DAO.ShipmentHistoryForDriver;
import com.tms.DAO.Update.DriverUpdate;
import com.tms.dto.DriverDTO;
import com.tms.dto.DriverLoginRequest;
import com.tms.entity.Driver;
import com.tms.service.interfaces.DriverEntityService;

@RestController
@RequestMapping("/api/driver")
@CrossOrigin(origins = {"http://localhost:3000","https://transportmanagementsys.netlify.app"},allowedHeaders = "*")
public class DriverController {

	@Autowired
	private DriverEntityService driverEntityService;
	
	@PostMapping("login")
	public ResponseEntity<String> Authenticate(@RequestBody DriverLoginRequest dlr) {
		return driverEntityService.Authenticate(dlr);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> saveDriver(@RequestBody DriverDTO driver) {
		return driverEntityService.saveDriver(driver);
	}
	
	@GetMapping
	public ResponseEntity<List<DriverDao>> getAllDrivers(){
		return driverEntityService.getAllDriver();
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<Driver> getDriverByUsername(@PathVariable String username){
		return driverEntityService.getDriverByUsername(username);
	}
	
	@GetMapping("/shipmenthistory/{id}")
	public ResponseEntity<List<ShipmentHistoryForDriver>> getShipmentHistory(@PathVariable Long id) {
		return driverEntityService.getShipmentHistory(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
		return driverEntityService.findById(id);		
	}
	
	@GetMapping("/update/{id}")
	public ResponseEntity<DriverUpdate> getDriverUpdateById(@PathVariable Long id) {
		return driverEntityService.findDriverUpdateByid(id);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateDriver(@RequestBody DriverUpdate d) {
		return driverEntityService.updateByid(d);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDriver(@PathVariable Long id){
		return driverEntityService.deleteDriver(id);
	}
	
}