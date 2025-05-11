package com.tms.dto;

import java.util.Date;

public class ShipmentDTO {

    
    private String vehicleType;
    private Double weight;
    private String origin;
    private String destination;
    private Date shipmentDate;
	
    // Getters and Setters
    
    
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
    
}
