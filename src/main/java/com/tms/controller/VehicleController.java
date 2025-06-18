package com.tms.controller;

import com.tms.DAO.VehicleDAO;
import com.tms.DAO.Update.VehicleUpdate;
import com.tms.dto.AssignRequest;
import com.tms.dto.DriverAssignRequest;
import com.tms.dto.VehicleDTO;
import com.tms.entity.Vehicle;
import com.tms.service.interfaces.VehicleEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://transportmanagementsys.netlify.app"},allowedHeaders = "*")
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleEntityService vehicleEntityService;

    @PostMapping("/add")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDTO vehicledto) {
    	return vehicleEntityService.saveVehicle(vehicledto);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignVehicleToShipment(@RequestBody AssignRequest request) {
        return vehicleEntityService.assignToShipment(request.getVehicleID(), request.getShipmentID());
    }
    
    @PostMapping("/assign/driver")
    public ResponseEntity<String> assignVehicleToDriver(@RequestBody DriverAssignRequest request) {
        return vehicleEntityService.assignToDriver(request.getVehicleID(), request.getDriverID());
    }
    
    @GetMapping 
    public ResponseEntity<List<VehicleDAO>> getAllVehicles() {
        return vehicleEntityService.getAllVehicles();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putVehicle(@PathVariable Long id, @RequestBody VehicleUpdate vehicle) {
    	return vehicleEntityService.updateByVehicleID(vehicle,id);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleEntityService.getVehicleById(id);
    }
    
    @GetMapping("/update/{id}")
    public ResponseEntity<VehicleUpdate> getVehicleUpdateById(@PathVariable Long id) {
        return vehicleEntityService.getVehicleUpdateById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return vehicleEntityService.deleteVehicle(id);
    }
    
    @GetMapping("/status/available")
    public ResponseEntity<List<VehicleDAO>> getAvailablevehicles(){
    	return  vehicleEntityService.findbyStatus(); 
    }
    
    @GetMapping("/status/assigned")
    public ResponseEntity<List<VehicleDAO>> getAssignedvehicles(){
    	return  vehicleEntityService.findbyDriverStatus(); 
    }
     
}
