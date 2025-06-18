package com.tms.service;

import com.tms.dto.LocationDto;
import com.tms.entity.Driver;
import com.tms.entity.DriverStatus;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;
import com.tms.exception.ShipmentIdNotfound;
import com.tms.repository.LocationRepository;
import com.tms.repository.ShipmentRepository;
import com.tms.service.interfaces.DriverEntityService;
import com.tms.service.interfaces.EmailEntityService;
import com.tms.service.interfaces.TrackingEntityService;
import com.tms.service.interfaces.UserEntityService;
import com.tms.service.interfaces.VehicleEntityService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Service; 

@Service
public class TrackingService implements TrackingEntityService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VehicleEntityService vehicleEntityService;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private DriverEntityService driverEntityService;
 
    @Autowired
    private ShipmentRepository shipmentRepository;
    
    @Autowired
    private EmailEntityService emailEntityService;
    
    @Override
    public ResponseEntity<Location> saveLocation(LocationDto location) {
        Location l = new Location(location);
    	locationRepository.save(l);
        return ResponseEntity.ok(l);
    }
    
    @Override
    public Location getLocation(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + id));
    }

    @Override
    public ResponseEntity<?> deleteLocation(Long id) throws MessagingException {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + id));

        Shipment shipment = location.getShipment();
        if (shipment != null) {
        	User user = shipment.getUser();
        	emailEntityService.sendShipmentDeliveredEmail(shipment, user , location);
            location.setShipment(null);
            shipment.setLocation(null);

            Vehicle vehicle = shipment.getVehicle();
            if (vehicle != null) {
                Driver driver = vehicle.getDriver();
                if (driver != null) {
                    driver.setVehicle(null);
                    driverEntityService.save(driver);
                }

                vehicle.setDriver(null);
                vehicle.setShipment(null);
                vehicle.setDriverstatus(DriverStatus.AVAILABLE);
                vehicle.setStatus(VehicleStatus.AVAILABLE);
                vehicleEntityService.save(vehicle);
            }

            shipment.setVehicle(null);

            if (user != null) {
                user.removeShipment(shipment);
                userEntityService.saveUser(user);
            }
            shipment.setStatus("Delivered");
            shipmentRepository.save(shipment);
        }
        return ResponseEntity.ok("Shipment Delivered");
    }

    @Override
	public Location saveLocation(Location location1) {
		return locationRepository.save(location1);
	}

	@Override
	public ResponseEntity<Location> getLocationByShipmentnumber(String str) {
		Shipment s = shipmentRepository.findByShipmentNumber(str).orElseThrow(()-> new ShipmentIdNotfound("Shipment ID not found"));
//    	System.out.println("Gets triggered");
//    	System.out.println(s);
    	if(s.getLocation()==null) return ResponseEntity.noContent().build();
    	return ResponseEntity.ok(s.getLocation());
	}
}
