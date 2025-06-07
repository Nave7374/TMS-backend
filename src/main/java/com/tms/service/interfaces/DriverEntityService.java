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

	String Authenticate(DriverLoginRequest dlr);
	
	String saveDriver(DriverDTO driver);
	
	List<DriverDao> getAllDriver();
	
	Optional<Driver> findById(Long id);
	
	Driver save(Driver driver);
	
	ResponseEntity<?> deleteDriver(Long id);
	
	List<ShipmentHistoryForDriver> getShipmentHistory(Long id);
	
	ResponseEntity<String> updateByid(DriverUpdate d);
	
	DriverUpdate findDriverUpdateByid(Long id);
	
	Optional<Driver> getDriverByUsername(String username);
	
}