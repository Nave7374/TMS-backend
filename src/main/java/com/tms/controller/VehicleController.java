package com.tms.controller;

import com.tms.DAO.VehicleDAO;
import com.tms.DAO.Update.VehicleUpdate;
import com.tms.dto.AssignRequest;
import com.tms.dto.DriverAssignRequest;
import com.tms.dto.VehicleDTO;
import com.tms.entity.Vehicle;
import com.tms.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add")
    public Vehicle createVehicle(@RequestBody VehicleDTO vehicledto) {
        Vehicle vehicle = new Vehicle(vehicledto);
    	return vehicleService.saveVehicle(vehicle);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignVehicleToShipment(@RequestBody AssignRequest request) {
        vehicleService.assignToShipment(request.getVehicleID(), request.getShipmentID());
        return ResponseEntity.ok("Assigned successfully");
    }
    
    @PostMapping("/assign/driver")
    public ResponseEntity<String> assignVehicleToDriver(@RequestBody DriverAssignRequest request) {
        vehicleService.assignToDriver(request.getVehicleID(), request.getDriverID());
        return ResponseEntity.ok("Assigned successfully");
    }
    
    @GetMapping 
    public List<VehicleDAO> getAllVehicles() {
        return vehicleService.getAllVehicles().stream().map(i->new VehicleDAO(i)).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putVehicle(@PathVariable Long id, @RequestBody VehicleUpdate vehicle) {
    	vehicleService.updateByVehicleID(vehicle,id);
    	return ResponseEntity.ok("Updated Successfully");
    }
    
    @GetMapping("/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }
    
    @GetMapping("/update/{id}")
    public VehicleUpdate getVehicleUpdateById(@PathVariable Long id) {
        return vehicleService.getVehicleUpdateById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle Deleted");
    }
    
    @GetMapping("/status/available")
    public List<VehicleDAO> getAvailablevehicles(){
    	return  vehicleService.findbyStatus().stream().map(i->new VehicleDAO(i)).collect(Collectors.toList()); 
    }
    
    @GetMapping("/status/assigned")
    public List<VehicleDAO> getAssignedvehicles(){
    	return  vehicleService.findbyDriverStatus().stream().map(i->new VehicleDAO(i)).collect(Collectors.toList()); 
    }
     
}
