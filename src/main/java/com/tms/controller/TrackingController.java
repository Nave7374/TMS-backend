package com.tms.controller;

import com.tms.dto.LocationDto;
import com.tms.entity.Location;
import com.tms.service.interfaces.TrackingEntityService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = {"http://localhost:3000","https://transportmanagementsys.netlify.app"},allowedHeaders = "*")
public class TrackingController {

	@Autowired
    private TrackingEntityService trackingEntityService;

    @PostMapping("/save")
    public ResponseEntity<Location> saveLocation(@RequestBody LocationDto location) {
        return trackingEntityService.saveLocation(location);
    }

    @GetMapping("/get/{str}")
    public ResponseEntity<Location> getLocation(@PathVariable String str) {
    	return trackingEntityService.getLocationByShipmentnumber(str);
     }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletLocation(@PathVariable Long id) throws MessagingException {
    	return trackingEntityService.deleteLocation(id);
   	}
}