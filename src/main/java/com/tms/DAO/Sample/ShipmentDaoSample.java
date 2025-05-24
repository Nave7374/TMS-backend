package com.tms.DAO.Sample;

import java.util.Date;

import com.tms.entity.Shipment;

public class ShipmentDaoSample {

	private Long id;
	private double weight;
    private String shipmentNumber;
    private String origin;
    private String destination;
    private String status; // e.g., 'in transit', 'delivered', etc.
    private String vehicleType;
    
    private Date shipmentDate;

    
    public ShipmentDaoSample(Shipment s) {
    	setId(s.getId());
    	setDestination(s.getDestination());
    	setOrigin(s.getOrigin());
    	setShipmentDate(s.getShipmentDate());
    	setShipmentNumber(s.getShipmentNumber());
    	setStatus(s.getStatus());
    	setVehicleType(s.getVehicleType());
    	setWeight(s.getWeight());
	}
    
	public Long getId() {
		return id;
	}

	public double getWeight() {
		return weight;
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

	public String getVehicleType() {
		return vehicleType;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	
}