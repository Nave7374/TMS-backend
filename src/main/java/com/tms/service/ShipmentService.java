package com.tms.service;

import com.tms.entity.Shipment;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;
import com.tms.repository.ShipmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    
    // Create or Update Shipment
    public Shipment saveShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    // Get all Shipments
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    // Get Shipment by ID
    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    // Delete Shipment
    public void deleteShipment(Long id) {
    	Shipment s = shipmentRepository.findById(id).orElse(null);
    	if(s.getVehicle()!=null) {
    		Vehicle v = s.getVehicle();
    		v.setStatus(VehicleStatus.AVAILABLE);
    		v.setShipment(null);
    		s.setVehicle(null);
    	}
    	if(s.getUser()!=null) {
    		s.getUser().removeShipment(s);
    	}
    	shipmentRepository.delete(s);
    }
    
    public Optional<Shipment> getbyshipmentNumber(String s){
    	return shipmentRepository.findByShipmentNumber(s);
    }

	public List<Shipment> findByUserId(Long id) {
		return shipmentRepository.findByUser_Id(id);
	}
      
}