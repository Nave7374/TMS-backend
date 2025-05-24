package com.tms.DAO;

import java.util.Date;

import com.tms.entity.Shipment;
import com.tms.entity.User;


public class ShipmentDao {

	private Long id;
	
	private User user;
	private double weight;
    private String shipmentNumber;
    private String origin;
    private String destination;
    private String status; // e.g., 'in transit', 'delivered', etc.
    private String vehicleType;
    
    private Date shipmentDate;
    
    private LocationDao location;
    
    private VehicleDAO vehicle;
    
    public ShipmentDao(Shipment s ) {
    	this.id=s.getId();
    	this.destination=s.getDestination();
    	if(s.getLocation()!=null)this.location=new LocationDao(s.getLocation());
    	this.origin=s.getOrigin();
    	this.shipmentDate=s.getShipmentDate();
    	this.shipmentNumber=s.getShipmentNumber();
    	this.status=s.getStatus();
    	if(s.getVehicle()!=null)this.vehicle=new VehicleDAO(s.getVehicle());
    	this.vehicleType=s.getVehicleType();
    	this.weight=s.getWeight();
    	if(s.getUser()!=null)this.user=s.getUser();
	}
    
    
	public Long getId() {
		return id;
	}
	public User getUser() {
		return user;
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
	public LocationDao getLocation() {
		return location;
	}
	public VehicleDAO getVehicle() {
		return vehicle;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
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
	public void setLocation(LocationDao location) {
		this.location = location;
	}
	public void setVehicle(VehicleDAO vehicle) {
		this.vehicle = vehicle;
	}
    
    
}