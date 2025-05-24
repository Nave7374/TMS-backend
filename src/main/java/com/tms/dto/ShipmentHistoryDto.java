package com.tms.dto;

import java.util.Date;

import com.tms.DAO.Sample.VehicleDaoSample;
import com.tms.entity.Location;
import com.tms.entity.Shipment;
import com.tms.entity.User;

public class ShipmentHistoryDto {

	private Long id;
	private VehicleDaoSample vehicle;
	private Location location;
	private double weight;
    private String shipmentNumber;
    private String origin;
    private String destination;
    private String status; // e.g., 'in transit', 'delivered', etc.
    private String vehicleType;
    
    private Date shipmentDate;
    private User user;

    public ShipmentHistoryDto() {
		// TODO Auto-generated constructor stub
	}
    
    public ShipmentHistoryDto(Shipment s){
    	setDestination(s.getDestination());
    	setLocation(s.getLocation());
    	setOrigin(s.getOrigin());
    	setShipmentDate(s.getShipmentDate());
    	setShipmentNumber(s.getShipmentNumber());
    	setStatus(s.getStatus());
    	if(s.getVehicle()!=null)setVehicle(new VehicleDaoSample(s.getVehicle()));
    	setVehicleType(s.getVehicleType());
    	setWeight(s.getWeight());
    	setId(s.getId());
    	setUser(s.getUser());
    }
    
    
	public VehicleDaoSample getVehicle() {
		return vehicle;
	}

	public Location getLocation() {
		return location;
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

	public void setVehicle(VehicleDaoSample vehicle) {
		this.vehicle = vehicle;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
	
}