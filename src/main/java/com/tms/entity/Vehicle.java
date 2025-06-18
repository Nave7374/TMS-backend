package com.tms.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tms.dto.VehicleDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "vehicles")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id"
		)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String registrationNumber;
    private String make;
    private String type;
    private String model;
    private int year;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;  // e.g., 'available', 'in use', etc.

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id")
//    @JsonManagedReference
    private Shipment shipment;
    
    @Enumerated(EnumType.STRING)
    private DriverStatus driverstatus;
    
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
//    @JsonBackReference
    private Driver driver;
    
    public Vehicle() {
		// TODO Auto-generated constructor stub
	}
    
    public Vehicle(VehicleDTO vehicle){
    	setDriverstatus(DriverStatus.AVAILABLE);
    	setStatus(VehicleStatus.AVAILABLE);
    	setMake(vehicle.getMake());
    	setModel(vehicle.getModel());
    	setRegistrationNumber(vehicle.getRegistrationNumber());
    	setType(vehicle.getType());
    	setYear(vehicle.getYear());
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

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setStatus(VehicleStatus assigned) {
		this.status = assigned;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public DriverStatus getDriverstatus() {
		return driverstatus;
	}

	public void setDriverstatus(DriverStatus driverstatus) {
		this.driverstatus = driverstatus;
	}
}
