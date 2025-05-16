package com.tms.controller;

import com.tms.dto.LocationDto;
import com.tms.dto.ShipmentDTO;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.service.ShipmentService;
import com.tms.service.TrackingService;
import com.tms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    	User user = userservice.findUserById(id).get();
    	Shipment s = new Shipment(shipment);
    	user.addShipments(s);
        return shipmentService.saveShipment(s);
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public Optional<Shipment> getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
    }
    
    @GetMapping("/user/{id}")
    public List<Shipment> getShipmentByUserId(@PathVariable Long id) {
    		return userservice.findUserById(id).get().getShipment();
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