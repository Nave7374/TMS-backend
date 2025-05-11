package com.tms.controller;

import com.tms.dto.ShipmentDTO;
import com.tms.entity.Shipment;
import com.tms.service.ShipmentService;

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

    @PostMapping
    public Shipment createShipment(@RequestBody ShipmentDTO shipment) {
        return shipmentService.saveShipment(new Shipment(shipment));
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
    
    @PostMapping("/book")
    public Shipment bookShipment(@RequestBody ShipmentDTO shipmentDTO) {
        return shipmentService.bookShipment(shipmentDTO);
    }
    
}
