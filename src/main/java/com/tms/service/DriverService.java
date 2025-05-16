package com.tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.entity.Driver;
import com.tms.entity.ShipmentHistory;
import com.tms.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	public List<Driver> getAllDriver(){
		return driverRepository.findAll();
	}
	
	public Optional<Driver> findById(Long id){
		return driverRepository.findById(id);
	}
	
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}
	
	public void deleteDriver(Long id) {
		driverRepository.deleteById(id);
	}

	public List<ShipmentHistory> getShipmentHistory(Long id) {
		Driver driver = driverRepository.findById(id).orElse(null);
		return driver.getShipments();
	}
	
}