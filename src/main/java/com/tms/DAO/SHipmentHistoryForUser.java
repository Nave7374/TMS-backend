package com.tms.DAO;

import java.util.Date;

import com.tms.DAO.Sample.UserDaoSample;
import com.tms.entity.UserShipmentHistory;

public class SHipmentHistoryForUser {

	
	private Long id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	
	private UserDaoSample user;
	
	public SHipmentHistoryForUser(UserShipmentHistory s){
		setId(s.getId());
		setDate(s.getDate());
		setDestination(s.getDestination());
		setOrigin(s.getOrigin());
		setShipmentnumber(s.getShipmentnumber());
		setUser(new UserDaoSample(s.getUser()));
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

	public UserDaoSample getUser() {
		return user;
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

	public void setUser(UserDaoSample user) {
		this.user = user;
	}
	
}