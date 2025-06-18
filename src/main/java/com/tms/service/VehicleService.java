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
import com.tms.exception.DriverIdNotFound;
import com.tms.exception.ShipmentIdNotfound;
import com.tms.exception.VehicleIdNotFound;
import com.tms.repository.ShipmentRepository;
import com.tms.repository.VehicleRepository;
import com.tms.service.interfaces.DriverEntityService;
import com.tms.service.interfaces.ShipmentHistoryEntityService;
import com.tms.service.interfaces.VehicleEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ResponseEntity<Vehicle> saveVehicle(VehicleDTO vehicledto) {
        return ResponseEntity.ok(vehicleRepository.save(new Vehicle(vehicledto)));
    }

    @Override
    public ResponseEntity<List<VehicleDAO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleRepository.findAll().stream().map(i->new VehicleDAO(i)).collect(Collectors.toList()));
    }

    @Override
    public 	ResponseEntity<Vehicle> getVehicleById(Long id) {
        return ResponseEntity.ok(vehicleRepository.findById(id).orElseThrow(()-> new VehicleIdNotFound("Vehicle ID not found")));
    }

    @Override
    public ResponseEntity<?> deleteVehicle(Long id) {
        Vehicle v = vehicleRepository.findById(id).orElseThrow(()-> new VehicleIdNotFound("Vehicle ID not found"));
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
    public ResponseEntity<List<VehicleDAO>> findbyStatus(){
    	return ResponseEntity.ok(vehicleRepository.findByStatus(VehicleStatus.AVAILABLE).stream().map(i->new VehicleDAO(i)).collect(Collectors.toList()));
    }

    @Override
	public ResponseEntity<String> assignToShipment(Long vehicleID, Long shipmentID) {
		Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(()-> new VehicleIdNotFound("Vehicle ID not found With the id "+vehicleID));
		Shipment shipment = shipmentRepository.findById(shipmentID).orElseThrow(() -> new ShipmentIdNotfound("Shipment Not Found With the id "+shipmentID));
		vehicle.setStatus(VehicleStatus.ASSIGNED);
		vehicle.setShipment(shipment);
		shipment.setVehicle(vehicle);
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok("Vehicle Assigned successfully");
	}

    @Override
	public ResponseEntity<String> assignToDriver(Long vehicleID, Long driverID) { 
		Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(()-> new VehicleIdNotFound("Vehicle ID not found with the id "+vehicleID));
		Driver driver = driverEntityService.findByIdOptional(driverID).orElseThrow(()->new DriverIdNotFound("Driver Not Found with the id "+driverID));
		vehicle.setDriverstatus(DriverStatus.ASSIGNED);
		vehicle.setDriver(driver);
		driver.setVehicle(vehicle);
		vehicle.getShipment().setStatus("Driver Assigned");
		ShipmentHistory s = new ShipmentHistory(vehicle.getShipment());
		s.setDriver(driver);
		driver.setShipments(s);
		shipmentHistoryEntityService.save(s);
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok("Driver Assigned successfully");
	}
    
    @Override
    public ResponseEntity<List<VehicleDAO>> findbyDriverStatus() {
		return ResponseEntity.ok(vehicleRepository.findByDriverstatusAndStatus(DriverStatus.AVAILABLE,VehicleStatus.ASSIGNED).stream().map(i->new VehicleDAO(i)).collect(Collectors.toList()));
	}

    @Override
	public ResponseEntity<String> updateByVehicleID(VehicleUpdate vehicle,Long id) {
		Vehicle v = vehicleRepository.findById(vehicle.getId()).orElseThrow(()-> new VehicleIdNotFound("Vehicle ID not found"));
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
	public ResponseEntity<VehicleUpdate> getVehicleUpdateById(Long id) {
		Vehicle v = getVehicleById(id).getBody();
		return ResponseEntity.ok(new VehicleUpdate(v));
	}
}
