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
import com.tms.exception.DriverIdNotFound;
import com.tms.repository.DriverRepository;
import com.tms.service.interfaces.DriverEntityService;

@Service
public class DriverService implements DriverEntityService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Override
	public ResponseEntity<String> Authenticate(DriverLoginRequest dlr) {
		Driver  d = driverRepository.findByUsername(dlr.getUsername()).orElseThrow(()-> new DriverIdNotFound("Username not found"));
		if(d!=null && encoder.matches(dlr.getPassword(), d.getPassword())) {
			return ResponseEntity.ok("Login Successfull!");
		}
		return ResponseEntity.badRequest().body("Invalid Credentials");
	}

	@Override
	public ResponseEntity<String> saveDriver(DriverDTO driver) {
		if(driverRepository.existsByUsername(driver.getUsername())) return ResponseEntity.badRequest().body("Username Already Exists");
		if(driverRepository.existsByEmail(driver.getEmail())) return ResponseEntity.badRequest().body("Email Already Exist");
		driver.setPassword(encoder.encode(driver.getPassword()));
		Driver d = new Driver(driver);
		driverRepository.save(d);
		return ResponseEntity.ok("Driver Saved Succesfully");
	}
	
	@Override
	public ResponseEntity<List<DriverDao>> getAllDriver(){
		return ResponseEntity.ok(driverRepository.findAll().stream().map(i->new DriverDao(i)).collect(Collectors.toList()));
	}
	
	@Override
	public ResponseEntity<Driver> findById(Long id){
		return ResponseEntity.ok(driverRepository.findById(id).orElseThrow(()->new DriverIdNotFound("Driver ID not found")));
	}
	
	@Override
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public ResponseEntity<?> deleteDriver(Long id) {
		Driver d = driverRepository.findById(id).orElseThrow(()-> new DriverIdNotFound("Driver ID not found"));
		if(d.getVehicle()!=null) {
			Vehicle v = d.getVehicle();
			if(v.getShipment()!=null) {
				throw new DriverIdNotFound("Driver is Working on Shipment");
			}
			v.setDriverstatus(DriverStatus.AVAILABLE);
			v.setDriver(null);
			d.setVehicle(null);
		}
		driverRepository.delete(d);
		return ResponseEntity.ok("Driver Deleted");		
	}

	@Override
	public  ResponseEntity<List<ShipmentHistoryForDriver>> getShipmentHistory(Long id) {
		Driver driver = driverRepository.findById(id).orElseThrow(()-> new DriverIdNotFound("Driver ID not found"));
		return ResponseEntity.ok(driver.getShipments().stream().map(i->new ShipmentHistoryForDriver(i)).collect(Collectors.toList()));
	}

	@Override
	public ResponseEntity<String> updateByid(DriverUpdate d) {
		Driver driver=driverRepository.findById(d.getId()).orElseThrow(()-> new DriverIdNotFound("Driver ID not found"));
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
	public ResponseEntity<DriverUpdate> findDriverUpdateByid(Long id) {
		Driver d = driverRepository.findById(id).orElseThrow(()-> new DriverIdNotFound("Driver ID not found"));
		return ResponseEntity.ok(new DriverUpdate(d));
	}

	@Override
	public ResponseEntity<Driver> getDriverByUsername(String username) {
		return ResponseEntity.ok(driverRepository.findByUsername(username).orElseThrow(()-> new DriverIdNotFound("Driver Username not found")));
	}

	@Override
	public Optional<Driver> findByIdOptional(Long id) {
		return driverRepository.findById(id);
	}
	
}