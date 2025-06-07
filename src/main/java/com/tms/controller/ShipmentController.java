package com.tms.controller;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.dto.LocationDto;
import com.tms.dto.ShipmentDTO;
import com.tms.dto.ShipmentHistoryDto;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.service.interfaces.ShipmentEntityService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = {"http://localhost:3000","https://transportmanagementsys.netlify.app"},allowedHeaders = "*")
public class ShipmentController {

    @Autowired
    private ShipmentEntityService shipmentEntityService;
    
    @PostMapping("/book/{id}")
    public ResponseEntity<?> createShipment(@PathVariable Long id,@RequestBody ShipmentDTO shipment) throws MessagingException {
    	return shipmentEntityService.createShipment(id,shipment);
    }
    
    @PostMapping("/location/{str}")
    public Location saveLocation(@PathVariable String str , @RequestBody LocationDto location ) {
    	return shipmentEntityService.saveLocation(str,location);
    }

    @GetMapping
    public List<ShipmentHistoryDto> getAllShipments() {
    	return shipmentEntityService.getAllShipments();
    }

    @GetMapping("/user/shipment/history")
    public List<SHipmentHistoryForUser> getUserShipmentHistory() {
        return shipmentEntityService.getUserShipmentHistory();
    }
    
    
    @GetMapping("/{id}")
    public Optional<Shipment> getShipmentById(@PathVariable Long id) {
        return shipmentEntityService.getShipmentById(id);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getShipmentByUserId(@PathVariable Long id) {
    	return shipmentEntityService.findByUserId(id);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShipment(@PathVariable Long id) {
    	return shipmentEntityService.deleteShipment(id);
    }

}