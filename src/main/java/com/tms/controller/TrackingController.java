package com.tms.controller;

import com.tms.dto.LocationDto;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.service.ShipmentService;
import com.tms.service.TrackingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class TrackingController {

	@Autowired
    private TrackingService trackingService;
	
	@Autowired
	private ShipmentService shipmentService;

    @PostMapping("/save")
    public Location saveLocation(@RequestBody LocationDto location) {
        return trackingService.saveLocation(new Location(location));
    }

    @GetMapping("/get/{str}")
    public Location getLocation(@PathVariable String str) {
    	Shipment s = shipmentService.getbyshipmentNumber(str).orElse(null);
//    	System.out.println("Gets triggered");
    	System.out.println(s);
        return s.getLocation();
    }
    
    @DeleteMapping("/{id}")
    public void deletLocation(@PathVariable Long id) {
    	trackingService.deleteLocation(id);
   	}
}