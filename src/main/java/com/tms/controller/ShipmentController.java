package com.tms.controller;

import com.tms.dto.LocationDto;
import com.tms.dto.ShipmentDTO;
import com.tms.dto.ShipmentHistoryDto;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.service.ShipmentService;
import com.tms.service.TrackingService;
import com.tms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private UserService userservice;
    
    @Autowired
    private TrackingService trackingService;
    
    @PostMapping("/book/{id}")
    public Shipment createShipment(@PathVariable Long id,@RequestBody ShipmentDTO shipment) {
    	User user = userservice.findUserById(id).orElse(null);
    	Shipment s = new Shipment(shipment);
    	user.addShipments(s);
    	userservice.saveUser(user);
        return s;
    }

    @GetMapping
    public List<ShipmentHistoryDto> getAllShipments() {
    	List<Shipment> l = shipmentService.getAllShipments();
    	List<ShipmentHistoryDto> history = l.stream().map(i-> new ShipmentHistoryDto(i)).collect(Collectors.toList());
    	return history;
    }

    @GetMapping("/{id}")
    public Optional<Shipment> getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.ok("Shipment Deleted");
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ShipmentHistoryDto>> getShipmentByUserId(@PathVariable Long id) {
//    		User user = userservice.findUserById(id).orElse(null);
//    		List<Shipment> l = user.getShipment();
//    		for(Shipment s:l) {
//    			System.out.println(s.getId()+" "+ s.getOrigin() + " "+ s.getDestination()+" " + s.getShipmentNumber());
//    		}
//    		return l;
    	List<Shipment>  list =  shipmentService.findByUserId(id);
    	List<ShipmentHistoryDto> history = list.stream().map(i-> new ShipmentHistoryDto(i)).collect(Collectors.toList());
    	for(ShipmentHistoryDto s:history) {
			System.out.println(s.getId()+" "+ s.getOrigin() + " "+ s.getDestination()+" " + s.getShipmentNumber());
		}
    	return ResponseEntity.ok(history);
    }   
    
    @PostMapping("/location/{str}")
    public Location saveLocation(@PathVariable String str , @RequestBody LocationDto location ) {
    	Shipment shipment = shipmentService.getbyshipmentNumber(str).orElse(null);
    	Location location1; 
    	if (shipment.getLocation() != null) {
    	 
    	        location1 = shipment.getLocation();
    	        location1.setLatitude(location.getLatitude());
    	        location1.setLongitude(location.getLongitude());
    	    } else {
    	        location1 = new Location(location);
    	        location1 = trackingService.saveLocation(location1);
    	        shipment.setLocation(location1);
    	        location1.setShipment(shipment);
    	    }
    	shipmentService.saveShipment(shipment);
    	return location1;
    }
}