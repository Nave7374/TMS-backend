package com.tms.controller;

import com.tms.dto.LocationDto;
import com.tms.entity.Location;
import com.tms.service.TrackingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class TrackingController {

    private final TrackingService trackingService;

    @Autowired
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("/save")
    public Location saveLocation(@RequestBody LocationDto location) {
        return trackingService.saveLocation(new Location(location));
    }

    @GetMapping("/get/{id}")
    public Location getLocation(@PathVariable Long id) {
        return trackingService.getLocation(id);
    }
}
