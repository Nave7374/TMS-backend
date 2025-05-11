package com.tms.service;

import com.tms.entity.Location;
import com.tms.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    private final LocationRepository locationRepository;

    @Autowired
    public TrackingService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocation(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
    }

    // More methods for tracking can be added here
}
