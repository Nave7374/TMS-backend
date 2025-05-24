package com.tms.DAO.Sample;

import com.tms.entity.DriverStatus;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class VehicleDaoSample {

	
	private Long id;
    
    private String registrationNumber;
    private String make;
    private String type;
    private String model;
    private int year;
	
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    

    @Enumerated(EnumType.STRING)
    private DriverStatus driverstatus;
    
    public VehicleDaoSample(Vehicle v) {
    	setId(v.getId());
    	setDriverstatus(v.getDriverstatus());
    	setMake(v.getMake());
    	setModel(v.getModel());
    	setRegistrationNumber(v.getRegistrationNumber());
    	setStatus(v.getStatus());
    	setType(v.getType());
    	setYear(v.getYear());
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

	public DriverStatus getDriverstatus() {
		return driverstatus;
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

	public void setDriverstatus(DriverStatus driverstatus) {
		this.driverstatus = driverstatus;
	}
    
}