package com.tms.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id"
		)
public class ShipmentHistory {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	private String vehicleno;
	
	public ShipmentHistory() {
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
//	@JsonManagedReference
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	public ShipmentHistory(Shipment s) {
		setDate(s.getShipmentDate());
		setDestination(s.getDestination());
		setOrigin(s.getOrigin());
		setShipmentnumber(s.getShipmentNumber());
		setVehicleno(s.getVehicle().getRegistrationNumber());
	}

	public int getId() {
		return id;
	}

	public String getOrigin() {
		return origin;
	}

	public String getShipmentnumber() {
		return shipmentnumber;
	}

	public String getDestination() {
		return destination;
	}

	public Date getDate() {
		return date;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setShipmentnumber(String shipmentnumber) {
		this.shipmentnumber = shipmentnumber;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}



		
}