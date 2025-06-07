package com.tms.service;

import com.tms.DAO.VehicleDAO;
import com.tms.DAO.Update.VehicleUpdate;
import com.tms.dto.VehicleDTO;
import com.tms.entity.Driver;
import com.tms.entity.DriverStatus;
import com.tms.entity.Shipment;
import com.tms.entity.ShipmentHistory;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;
import com.tms.exception.ResourceNotFoundException;
import com.tms.repository.ShipmentRepository;
import com.tms.repository.VehicleRepository;
import com.tms.service.interfaces.DriverEntityService;
import com.tms.service.interfaces.ShipmentHistoryEntityService;
import com.tms.service.interfaces.VehicleEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService implements VehicleEntityService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ShipmentHistoryEntityService shipmentHistoryEntityService; 
    
    @Autowired
    private DriverEntityService driverEntityService;
    
    @Override
	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
    
    @Override
    public Vehicle saveVehicle(VehicleDTO vehicledto) {
        return vehicleRepository.save(new Vehicle(vehicledto));
    }

    @Override
    public List<VehicleDAO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(i->new VehicleDAO(i)).collect(Collectors.toList());
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public ResponseEntity<?> deleteVehicle(Long id) {
        Vehicle v = vehicleRepository.findById(id).orElse(null);
        if (v.getShipment() != null) {
//            Shipment s = v.getShipment();
            return ResponseEntity.badRequest().body("Shipment Assigned for the Vehicle cannot Delete the vehicle");
//            s.setVehicle(null);
//            s.setStatus("Driver Will Be Assigned Shortly");
//            v.setShipment(null);
        }
        if (v.getDriver() != null) {
            Driver d = v.getDriver();
            d.setVehicle(null);
            v.setDriver(null);
        }
        vehicleRepository.delete(v);
        return ResponseEntity.ok("Vehicle Deleted");
    }
    
    @Override
    public List<VehicleDAO> findbyStatus(){
    	return vehicleRepository.findByStatus(VehicleStatus.AVAILABLE).stream().map(i->new VehicleDAO(i)).collect(Collectors.toList());
    }

    @Override
	public ResponseEntity<String> assignToShipment(Long vehicleID, Long shipmentID) {
		Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("Vehicle Not Found With the id "+vehicleID));
		Shipment shipment = shipmentRepository.findById(shipmentID).orElseThrow(() -> new ResourceNotFoundException("Shipment Not Found With the id "+shipmentID));
		vehicle.setStatus(VehicleStatus.ASSIGNED);
		vehicle.setShipment(shipment);
		shipment.setVehicle(vehicle);
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok("Assigned successfully");
	}

    @Override
	public ResponseEntity<String> assignToDriver(Long vehicleID, Long driverID) { 
		Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(()->new ResourceNotFoundException("Vehicle Not Found with the id "+vehicleID));
		Driver driver = driverEntityService.findById(vehicleID).orElseThrow(()->new ResourceNotFoundException("Vehicle Not Found with the id "+driverID));
		vehicle.setDriverstatus(DriverStatus.ASSIGNED);
		vehicle.setDriver(driver);
		driver.setVehicle(vehicle);
		vehicle.getShipment().setStatus("Driver Assigned");
		ShipmentHistory s = new ShipmentHistory(vehicle.getShipment());
		s.setDriver(driver);
		driver.setShipments(s);
		shipmentHistoryEntityService.save(s);
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok("Assigned successfully");
	}
    
    @Override
    public List<VehicleDAO> findbyDriverStatus() {
		return vehicleRepository.findByDriverstatusAndStatus(DriverStatus.AVAILABLE,VehicleStatus.ASSIGNED).stream().map(i->new VehicleDAO(i)).collect(Collectors.toList());
	}

    @Override
	public ResponseEntity<String> updateByVehicleID(VehicleUpdate vehicle,Long id) {
		Vehicle v = vehicleRepository.findById(vehicle.getId()).orElse(null);
		v.setMake(vehicle.getMake());
		v.setModel(vehicle.getModel());
		v.setRegistrationNumber(vehicle.getRegistrationNumber());
		v.setStatus(vehicle.getStatus());
		v.setYear(vehicle.getYear());
		v.setType(vehicle.getType());
		vehicleRepository.save(v);
		return ResponseEntity.ok("Updated Successfully");
	}

    @Override
	public VehicleUpdate getVehicleUpdateById(Long id) {
		Vehicle v = getVehicleById(id).orElse(null);
		return new VehicleUpdate(v);
	}
}
