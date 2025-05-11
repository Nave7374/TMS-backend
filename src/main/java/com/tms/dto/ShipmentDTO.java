package com.tms.dto;

import java.util.Date;

public class ShipmentDTO {

    private Long id;
    
    private String vehicleType;
    private Double weight;
    private String shipmentNumber;
    private String origin;
    private String destination;
    private String status;
    private Date shipmentDate;
	
    // Getters and Setters
    
    public Long getId() {
		return id;
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
