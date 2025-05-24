package com.tms.DAO.Update;

import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;

public class VehicleUpdate {

	private Long id;
	private String registrationNumber;
	private String make;
	private String type;
	private String model;
	private int year;
	private VehicleStatus status;
	
	
	public VehicleUpdate() {
	}
	
	public VehicleUpdate(Vehicle v) {
		this.id=v.getId();
		this.make=v.getMake();
		this.model=v.getModel();
		this.registrationNumber=v.getRegistrationNumber();
		this.status=v.getStatus();
		this.type=v.getType();
		this.year=v.getYear();
	}
	
	public Long getId() {
		return id; 
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getType() {
		return type;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getYear() {
		return year;
	}
	
	public VehicleStatus getStatus() {
		return status;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setStatus(VehicleStatus status) {
		this.status = status;
	}
	
}