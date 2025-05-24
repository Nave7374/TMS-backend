package com.tms.DAO;

import java.util.Date;

import com.tms.entity.User;
import com.tms.entity.UserShipmentHistory;

public class SHipmentHistoryForUser {

	
	private int id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	
	private User user;
	
	public SHipmentHistoryForUser(UserShipmentHistory s){
		setId(s.getId());
		setDate(s.getDate());
		setDestination(s.getDestination());
		setOrigin(s.getOrigin());
		setShipmentnumber(s.getShipmentnumber());
		setUser(s.getUser());
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

	public User getUser() {
		return user;
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

	public void setUser(User user) {
		this.user = user;
	}
	
}