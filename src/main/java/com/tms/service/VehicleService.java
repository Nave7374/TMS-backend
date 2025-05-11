package com.tms.service;

import com.tms.entity.Shipment;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;
import com.tms.exception.ResourceNotFoundException;
import com.tms.repository.ShipmentRepository;
import com.tms.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;
    
    // Create or Update Vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Get all Vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get Vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Delete Vehicle
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
    
    public List<Vehicle> findbyStatus(){
    	return vehicleRepository.findByStatus(VehicleStatus.AVAILABLE);
    }

	public void assignToShipment(Long vehicleID, Long shipmentID) {
		Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("Vehicle Not Found With the id "+vehicleID));
		Shipment shipment = shipmentRepository.findById(shipmentID).orElseThrow(() -> new ResourceNotFoundException("Shipment Not Found With the id "+shipmentID));
		vehicle.setStatus(VehicleStatus.ASSIGNED);
		vehicle.setShipment(shipment);
		shipment.setVehicle(vehicle);
		vehicleRepository.save(vehicle);
	}
}
