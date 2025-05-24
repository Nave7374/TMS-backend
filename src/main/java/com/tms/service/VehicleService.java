package com.tms.service;

import com.tms.DAO.Update.VehicleUpdate;
import com.tms.entity.Driver;
import com.tms.entity.DriverStatus;
import com.tms.entity.Shipment;
import com.tms.entity.ShipmentHistory;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;
import com.tms.exception.ResourceNotFoundException;
import com.tms.repository.DriverRepository;
import com.tms.repository.ShipmentHistoryRepository;
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

    @Autowired
    private ShipmentHistoryRepository historyRepository; 
    
    @Autowired
    private DriverRepository driverRepository; 
    
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public void deleteVehicle(Long id) {
        Vehicle v = vehicleRepository.findById(id).orElse(null);
        if (v.getDriver() != null) {
            Driver d = v.getDriver();
            d.setVehicle(null);
            v.setDriver(null);
        }
        if (v.getShipment() != null) {
            Shipment s = v.getShipment();
            s.setVehicle(null);
            s.setStatus("Driver Will Be Assigned Shortly");
            v.setShipment(null);
        }
        vehicleRepository.delete(v);
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

	public void assignToDriver(Long vehicleID, Long driverID) { 
		Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(()->new ResourceNotFoundException("Vehicle Not Found with the id "+vehicleID));
		Driver driver = driverRepository.findById(vehicleID).orElseThrow(()->new ResourceNotFoundException("Vehicle Not Found with the id "+driverID));
		vehicle.setDriverstatus(DriverStatus.ASSIGNED);
		vehicle.setDriver(driver);
		driver.setVehicle(vehicle);
		vehicle.getShipment().setStatus("Driver Assigned");
		ShipmentHistory s = new ShipmentHistory(vehicle.getShipment());
		s.setDriver(driver);
		driver.setShipments(s);
		historyRepository.save(s);
		vehicleRepository.save(vehicle);
	}

	public List<Vehicle> findbyDriverStatus() {
		return vehicleRepository.findByDriverstatusAndStatus(DriverStatus.AVAILABLE,VehicleStatus.ASSIGNED);
	}

	public void updateByVehicleID(VehicleUpdate vehicle,Long id) {
		Vehicle v = vehicleRepository.findById(vehicle.getId()).orElse(null);
		v.setMake(vehicle.getMake());
		v.setModel(vehicle.getModel());
		v.setRegistrationNumber(vehicle.getRegistrationNumber());
		v.setStatus(vehicle.getStatus());
		v.setYear(vehicle.getYear());
		v.setType(vehicle.getType());
		vehicleRepository.save(v);
	}

	public VehicleUpdate getVehicleUpdateById(Long id) {
		Vehicle v = getVehicleById(id).orElse(null);
		return new VehicleUpdate(v);
	}
}
