package com.tms.service;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.dto.LocationDto;
import com.tms.dto.ShipmentDTO;
import com.tms.dto.ShipmentHistoryDto;
import com.tms.entity.DriverStatus;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;
import com.tms.exception.ShipmentIdNotfound;
import com.tms.exception.UserIdNotFound;
import com.tms.repository.ShipmentRepository;
import com.tms.service.interfaces.EmailEntityService;
import com.tms.service.interfaces.ShipmentEntityService;
import com.tms.service.interfaces.TrackingEntityService;
import com.tms.service.interfaces.UserEntityService;
import com.tms.service.interfaces.UserShipmenthistoryEntityService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipmentService implements ShipmentEntityService {

	@Autowired
	private UserEntityService userEntityService;
	
    @Autowired
    private ShipmentRepository shipmentRepository;
    
    @Autowired
    private EmailEntityService emailEntityService;
    
    @Autowired
    private TrackingEntityService trackingEntityService;
    
    @Autowired
    private UserShipmenthistoryEntityService userShipmenthistoryEntityService;
    
    @Override
    public Shipment saveShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public ResponseEntity<?> createShipment(Long id, ShipmentDTO shipment) throws MessagingException {
		User user = userEntityService.findUserById(id).orElseThrow(()-> new UserIdNotFound("User ID not found"));
    	Shipment s = new Shipment(shipment);
    	user.addShipments(s);
    	userEntityService.saveUser(user);
    	return emailEntityService.sendConfirmationMail(user,s);
	}
    
    @Override
    public ResponseEntity<List<ShipmentHistoryDto>> getAllShipments() {
    	List<Shipment> l = shipmentRepository.findAll();
    	List<ShipmentHistoryDto> history = l.stream().map(i-> new ShipmentHistoryDto(i)).collect(Collectors.toList());
    	return ResponseEntity.ok(history);
    }
    
    @Override
    public ResponseEntity<Shipment> getShipmentById(Long id) {
        return ResponseEntity.ok(shipmentRepository.findById(id).orElseThrow(()-> new ShipmentIdNotfound("Shipment ID not found")));
    }
    
    @Override
    public ResponseEntity<?> deleteShipment(Long id) {
    	Shipment s = shipmentRepository.findById(id).orElseThrow(()-> new ShipmentIdNotfound("Shipment ID not found"));
    	if(s.getVehicle()!=null) {
    		Vehicle v = s.getVehicle();
    		if(v.getDriver()!=null) {
    			v.getDriver().setVehicle(null);
    			v.setDriver(null);
    			v.setDriverstatus(DriverStatus.AVAILABLE);
    		}
    		v.setStatus(VehicleStatus.AVAILABLE);
    		v.setShipment(null);
    		s.setVehicle(null);
    	}
    	if(s.getUser()!=null) {
    		s.getUser().removeShipment(s);
    	}
    	shipmentRepository.delete(s);
    	return ResponseEntity.ok("Shipment Deleted");
    }
    
    @Override
    public Optional<Shipment> getbyshipmentNumber(String s){
    	return shipmentRepository.findByShipmentNumber(s);
    }

    @Override
	public ResponseEntity<?> findByUserId(Long id) {
		List<Shipment> list = shipmentRepository.findByUser_Id(id);
		List<ShipmentHistoryDto> history = list.stream().map(i-> new ShipmentHistoryDto(i)).collect(Collectors.toList());
		return ResponseEntity.ok(history);
	}

    @Override
	public ResponseEntity<Location> saveLocation(String str, LocationDto location) {
		Shipment shipment = shipmentRepository.findByShipmentNumber(str).orElseThrow(()-> new ShipmentIdNotfound("Shipment ID not found"));
    	Location location1 = shipment.getLocation();
    	if (location1!= null) {
    	        location1.setLatitude(location.getLatitude());
    	        location1.setLongitude(location.getLongitude());
    	        }
    	else {
    		location1 = new Location(location);
    	    location1 = trackingEntityService.saveLocation(location1);
    	    shipment.setLocation(location1);
    	    location1.setShipment(shipment);
    	    }
    	shipmentRepository.save(shipment);
    	return ResponseEntity.ok(location1);
	}

    @Override
	public ResponseEntity<List<SHipmentHistoryForUser>> getUserShipmentHistory() {
		return ResponseEntity.ok(userShipmenthistoryEntityService.getAllShipmentHistory());
	}

	@Override
	public Optional<Shipment> findById(Long shipmentID) {
		return shipmentRepository.findById(shipmentID);
	}
}