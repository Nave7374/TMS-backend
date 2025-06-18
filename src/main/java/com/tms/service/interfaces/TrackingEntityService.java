package com.tms.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.tms.dto.LocationDto;
import com.tms.entity.Location;

import jakarta.mail.MessagingException;

public interface TrackingEntityService {

	ResponseEntity<Location> saveLocation(LocationDto location);

	ResponseEntity<?> deleteLocation(Long id) throws MessagingException;

	Location getLocation(Long id);

	Location saveLocation(Location location);

	ResponseEntity<Location> getLocationByShipmentnumber(String str);

}
