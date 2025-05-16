package com.tms.service;

import com.tms.entity.*;
import com.tms.repository.LocationRepository;
import com.tms.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private ShipmentService shipmentService;

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocation(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + id));
    }

    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + id));

        Shipment shipment = location.getShipment();
        if (shipment != null) {
            location.setShipment(null);
            shipment.setLocation(null);

            Vehicle vehicle = shipment.getVehicle();
            if (vehicle != null) {
                Driver driver = vehicle.getDriver();
                if (driver != null) {
                    driver.setVehicle(null);
                    driverService.save(driver);
                }

                vehicle.setDriver(null);
                vehicle.setShipment(null);
                vehicle.setDriverstatus(DriverStatus.AVAILABLE);
                vehicle.setStatus(VehicleStatus.AVAILABLE);
                vehicleRepository.save(vehicle);
            }

            shipment.setVehicle(null);

            User user = shipment.getUser();
            if (user != null) {
                user.removeShipment(shipment);
                userService.saveUser(user);
            }

            shipmentService.deleteShipment(shipment.getId());
        }

        locationRepository.delete(location);
    }
}
