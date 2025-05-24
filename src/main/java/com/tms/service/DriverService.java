package com.tms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.DAO.ShipmentHistoryForDriver;
import com.tms.DAO.Update.DriverUpdate;
import com.tms.entity.Driver;
import com.tms.entity.DriverStatus;
import com.tms.entity.Vehicle;
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
		Driver d = findById(id).orElse(null);
		if(d.getVehicle()!=null) {
			Vehicle v = d.getVehicle();
			v.setDriverstatus(DriverStatus.AVAILABLE);
			v.setDriver(null);
			d.setVehicle(null);
		}
		driverRepository.delete(d);
	}

	public List<ShipmentHistoryForDriver> getShipmentHistory(Long id) {
		Driver driver = driverRepository.findById(id).orElse(null);
		return driver.getShipments().stream().map(i->new ShipmentHistoryForDriver(i)).collect(Collectors.toList());
	}

	public void updateByid(DriverUpdate d) {
		Driver driver=driverRepository.findById(d.getId()).orElse(null);
		driver.setAge(d.getAge());
		driver.setEmail(d.getEmail());
		driver.setFirstname(d.getFirstname());
		driver.setLastname(d.getLastname());
		driver.setPhone(d.getPhone());
		driver.setUsername(d.getUsername());
		driverRepository.save(driver);
	}

	public DriverUpdate findDriverUpdateByid(Long id) {
		Driver d = driverRepository.findById(id).orElse(null);
		return new DriverUpdate(d);
	}
	
}