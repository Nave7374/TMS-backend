package com.tms.entity;

import java.util.Date;
import java.util.UUID;

import com.tms.dto.ShipmentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Shipment() {
    	shipmentNumber=generateShipmentNumber();
	}
    
    public Shipment(ShipmentDTO s){
    	shipmentNumber=generateShipmentNumber();
    	origin = s.getOrigin();
    	destination = s.getDestination();
    	status = s.getStatus();
    	vehicleType = s.getVehicleType();
    	shipmentDate = s.getShipmentDate();
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    
    @OneToOne(mappedBy = "shipment")
    private Vehicle vehicle;
    
    private String shipmentNumber;
    private String origin;
    private String destination;
    private String status; // e.g., 'in transit', 'delivered', etc.
    private String vehicleType;
    
    private Date shipmentDate;
    
    // Getters and Setters

	public Long getId() {
		return id;
	}
	
	private String generateShipmentNumber() {
        return "SHIP-" + UUID.randomUUID().toString();
    }

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getStatus() {
		return status;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Location getLocation() {
		return location;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


}
