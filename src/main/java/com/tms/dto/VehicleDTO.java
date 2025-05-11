package com.tms.dto;

public class VehicleDTO {


    private String registrationNumber;
    private String make;
    private String model;
    private String type; 
    private int year;
    private String status;
	
    // Getters and Setters
    
    
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public int getYear() {
		return year;
	}
	public String getStatus() {
		return status;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
