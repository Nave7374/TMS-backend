package com.tms.DAO.Sample;

import java.util.Date;

import com.tms.entity.ShipmentHistory;

public class ShipmentHistoryForDriverSample {

	private Long id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	private String vehicleno;
	
	public ShipmentHistoryForDriverSample(ShipmentHistory s) {
		this.id=s.getId();
		this.date=s.getDate();
		this.destination=s.getDestination();
		this.origin=s.getOrigin();
		this.shipmentnumber=s.getShipmentnumber();
		this.vehicleno=s.getVehicleno();
	}

	public Long getId() {
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

	public void setId(Long id) {
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
	
}