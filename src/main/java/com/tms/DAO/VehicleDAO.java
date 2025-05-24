package com.tms.DAO;

import com.tms.DAO.Sample.DriverSampleDto;
import com.tms.DAO.Sample.ShipmentDaoSample;
import com.tms.entity.DriverStatus;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class VehicleDAO {
	
	private Long id;
    
    private String registrationNumber;
    private String make;
    private String type;
    private String model;
    private int year;
    
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    
    private ShipmentDaoSample shipment;
    
    @Enumerated(EnumType.STRING)
    private DriverStatus driverstatus;
    
    private DriverSampleDto driver;
    
    public VehicleDAO(Vehicle v) {
    	setId(v.getId());
    	if(v.getDriver()!=null)setDriver(new DriverSampleDto(v.getDriver()));
    	setDriverstatus(v.getDriverstatus());
    	setMake(v.getMake());
    	setModel(v.getModel());
    	setRegistrationNumber(v.getRegistrationNumber());
    	if(v.getShipment()!=null)setShipment(new ShipmentDaoSample(v.getShipment()));
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
	public ShipmentDaoSample getShipment() {
		return shipment;
	}
	public DriverStatus getDriverstatus() {
		return driverstatus;
	}
	public DriverSampleDto getDriver() {
		return driver;
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
	public void setShipment(ShipmentDaoSample shipment) {
		this.shipment = shipment;
	}
	public void setDriverstatus(DriverStatus driverstatus) {
		this.driverstatus = driverstatus;
	}
	public void setDriver(DriverSampleDto driver) {
		this.driver = driver;
	}
}