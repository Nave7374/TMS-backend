package com.tms.DAO.Sample;

import java.util.Date;

import com.tms.entity.UserShipmentHistory;

public class ShipmentHistoryForUserSample {

	private Long id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	
	public ShipmentHistoryForUserSample(UserShipmentHistory s){
		setId(s.getId());
		setDate(s.getDate());
		setDestination(s.getDestination());
		setOrigin(s.getOrigin());
		setShipmentnumber(s.getShipmentnumber());
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

	public void setId(Long long1) {
		this.id = long1;
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
	
	
	
}