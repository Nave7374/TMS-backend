package com.tms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity; 

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.dto.LocationDto;
import com.tms.dto.ShipmentDTO;
import com.tms.dto.ShipmentHistoryDto;
import com.tms.entity.Location;
import com.tms.entity.Shipment;

import jakarta.mail.MessagingException; 
 
public interface ShipmentEntityService {

	Shipment saveShipment(Shipment shipment);
	
	ResponseEntity<?> createShipment(Long id, ShipmentDTO shipment) throws MessagingException;
	
	ResponseEntity<List<ShipmentHistoryDto>> getAllShipments();
	
	ResponseEntity<Shipment> getShipmentById(Long id);
	
	ResponseEntity<?> deleteShipment(Long id);
	
	Optional<Shipment> getbyshipmentNumber(String s);
	
	ResponseEntity<?> findByUserId(Long id);
	
	ResponseEntity<Location> saveLocation(String str, LocationDto location);
	
	ResponseEntity<List<SHipmentHistoryForUser>> getUserShipmentHistory();

	Optional<Shipment> findById(Long shipmentID);
	
}