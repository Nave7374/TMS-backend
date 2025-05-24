package com.tms.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "usershipmenthistory")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id"
		)
public class UserShipmentHistory {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public UserShipmentHistory() {
	}
	
	public UserShipmentHistory(Shipment s) {
		setDate(s.getShipmentDate());
		setOrigin(s.getOrigin());
		setDestination(s.getDestination());
		setShipmentnumber(s.getShipmentNumber());
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
