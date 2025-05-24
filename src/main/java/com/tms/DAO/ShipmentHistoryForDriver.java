package com.tms.DAO;

import java.util.Date;

import com.tms.DAO.Sample.DriverSampleDto;
import com.tms.entity.ShipmentHistory;

public class ShipmentHistoryForDriver {

	private Long id;
	private String origin;
	private String shipmentnumber;
	private String destination;
	private Date date;
	private String vehicleno;
	private DriverSampleDto driver;
	
	public ShipmentHistoryForDriver(ShipmentHistory s) {
		this.id=s.getId();
		this.date=s.getDate();
		this.destination=s.getDestination();
		if(s.getDriver()!=null)this.driver=new DriverSampleDto(s.getDriver());
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
	public DriverSampleDto getDriver() {
		return driver;
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
	public void setDriver(DriverSampleDto driver) {
		this.driver = driver;
	}
	
}