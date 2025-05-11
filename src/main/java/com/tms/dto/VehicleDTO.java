package com.tms.dto;

public class VehicleDTO {


    private String registrationNumber;
    private String make;
    private String model;
    private String type; 
    private int year;
	
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
