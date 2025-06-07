package com.tms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tms.DAO.VehicleDAO;
import com.tms.DAO.Update.VehicleUpdate;
import com.tms.dto.VehicleDTO;
import com.tms.entity.Vehicle;

public interface VehicleEntityService {

	Vehicle saveVehicle(VehicleDTO vehicledto);

	ResponseEntity<String> assignToShipment(Long vehicleID, Long shipmentID);

	ResponseEntity<String> assignToDriver(Long vehicleID, Long driverID);

	List<VehicleDAO> getAllVehicles();

	ResponseEntity<String> updateByVehicleID(VehicleUpdate vehicle, Long id);

	Optional<Vehicle> getVehicleById(Long id);

	VehicleUpdate getVehicleUpdateById(Long id);

	ResponseEntity<?> deleteVehicle(Long id);

	List<VehicleDAO> findbyStatus();

	List<VehicleDAO> findbyDriverStatus();

	void save(Vehicle vehicle);

}
