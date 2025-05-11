package com.tms.controller;

import com.tms.dto.VehicleDTO;
import com.tms.entity.Vehicle;
import com.tms.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PutMapping("/{id}")
    public Vehicle putVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id); // Ensure the ID of the vehicle is set before saving.
        return vehicleService.saveVehicle(vehicle); // Call the service method to save/update the vehicle.
    }
    
    @GetMapping("/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
