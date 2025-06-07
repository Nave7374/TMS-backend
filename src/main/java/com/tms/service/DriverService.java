package com.tms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tms.DAO.DriverDao;
import com.tms.DAO.ShipmentHistoryForDriver;
import com.tms.DAO.Update.DriverUpdate;
import com.tms.dto.DriverDTO;
import com.tms.dto.DriverLoginRequest;
import com.tms.entity.Driver;
import com.tms.entity.DriverStatus;
import com.tms.entity.Vehicle;
import com.tms.repository.DriverRepository;
import com.tms.service.interfaces.DriverEntityService;

@Service
public class DriverService implements DriverEntityService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Override
	public String Authenticate(DriverLoginRequest dlr) {
		Driver  d = driverRepository.findByUsername(dlr.getUsername()).orElse(null);
		if(d!=null && encoder.matches(dlr.getPassword(), d.getPassword())) {
			return "Login Successfull!";
		}
		return "Invalid Credentials";
	}

	@Override
	public String saveDriver(DriverDTO driver) {
		if(driverRepository.existsByUsername(driver.getUsername())) return "Username Already Exists";
		driver.setPassword(encoder.encode(driver.getPassword()));
		Driver d = new Driver(driver);
		driverRepository.save(d);
		return "Driver Saved Succesfully";
	}
	
	@Override
	public List<DriverDao> getAllDriver(){
		return driverRepository.findAll().stream().map(i->new DriverDao(i)).collect(Collectors.toList());
	}
	
	@Override
	public Optional<Driver> findById(Long id){
		return driverRepository.findById(id);
	}
	
	@Override
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}
	
	@Override
	public ResponseEntity<?> deleteDriver(Long id) {
		Driver d = findById(id).orElse(null);
		if(d.getVehicle()!=null) {
			Vehicle v = d.getVehicle();
			if(v.getShipment()!=null) {
				return ResponseEntity.badRequest().body("Driver is Working on Shipment");
			}
			v.setDriverstatus(DriverStatus.AVAILABLE);
			v.setDriver(null);
			d.setVehicle(null);
		}
		driverRepository.delete(d);
		return ResponseEntity.ok("Driver Deleted");		
	}

	@Override
	public List<ShipmentHistoryForDriver> getShipmentHistory(Long id) {
		Driver driver = driverRepository.findById(id).orElse(null);
		return driver.getShipments().stream().map(i->new ShipmentHistoryForDriver(i)).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<String> updateByid(DriverUpdate d) {
		Driver driver=driverRepository.findById(d.getId()).orElse(null);
		driver.setAge(d.getAge());
		driver.setEmail(d.getEmail());
		driver.setFirstname(d.getFirstname());
		driver.setLastname(d.getLastname());
		driver.setPhone(d.getPhone());
		driver.setUsername(d.getUsername());
		driverRepository.save(driver);
		return ResponseEntity.ok("Updated");
	}

	@Override
	public DriverUpdate findDriverUpdateByid(Long id) {
		Driver d = driverRepository.findById(id).orElse(null);
		return new DriverUpdate(d);
	}

	@Override
	public Optional<Driver> getDriverByUsername(String username) {
		return driverRepository.findByUsername(username);
	}
	
}