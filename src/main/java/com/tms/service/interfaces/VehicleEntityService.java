package com.tms.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tms.DAO.VehicleDAO;
import com.tms.DAO.Update.VehicleUpdate;
import com.tms.dto.VehicleDTO;
import com.tms.entity.Vehicle;

public interface VehicleEntityService {

	ResponseEntity<Vehicle> saveVehicle(VehicleDTO vehicledto);

	ResponseEntity<String> assignToShipment(Long vehicleID, Long shipmentID);

	ResponseEntity<String> assignToDriver(Long vehicleID, Long driverID);

	ResponseEntity<List<VehicleDAO>> getAllVehicles();

	ResponseEntity<String> updateByVehicleID(VehicleUpdate vehicle, Long id);

	ResponseEntity<Vehicle> getVehicleById(Long id);

	ResponseEntity<VehicleUpdate> getVehicleUpdateById(Long id);

	ResponseEntity<?> deleteVehicle(Long id);

	ResponseEntity<List<VehicleDAO>> findbyStatus();

	ResponseEntity<List<VehicleDAO>> findbyDriverStatus();

	void save(Vehicle vehicle);

}
