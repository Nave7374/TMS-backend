package com.tms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity; 

import com.tms.DAO.DriverDao;
import com.tms.DAO.ShipmentHistoryForDriver;
import com.tms.DAO.Update.DriverUpdate;
import com.tms.dto.DriverDTO;
import com.tms.dto.DriverLoginRequest;
import com.tms.entity.Driver;

 
public interface DriverEntityService {

	ResponseEntity<String> Authenticate(DriverLoginRequest dlr);
	
	ResponseEntity<String> saveDriver(DriverDTO driver);
	
	ResponseEntity<List<DriverDao>> getAllDriver();
	
	ResponseEntity<Driver> findById(Long id);

	Optional<Driver> findByIdOptional(Long id);
	
	Driver save(Driver driver);
	
	ResponseEntity<?> deleteDriver(Long id);
	
	ResponseEntity<List<ShipmentHistoryForDriver>> getShipmentHistory(Long id);
	
	ResponseEntity<String> updateByid(DriverUpdate d);
	
	ResponseEntity<DriverUpdate> findDriverUpdateByid(Long id);
	
	ResponseEntity<Driver> getDriverByUsername(String username);
	
}