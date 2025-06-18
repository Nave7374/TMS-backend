package com.tms.DAO;

import java.time.LocalDateTime;

import com.tms.DAO.Sample.ShipmentDaoSample;
import com.tms.entity.Location;

public class LocationDao {
	
	private Long id;
    
    private double latitude;
    private double longitude;
    private ShipmentDaoSample shipment;
    private LocalDateTime inserttime;
    private LocalDateTime updatetime;
    
    public LocationDao(Location l) {
    	this.id=l.getId();
    	this.longitude=l.getLongitude();
    	this.latitude=l.getLatitude();
    	if(l.getShipment()!=null)this.shipment=new ShipmentDaoSample(l.getShipment());
    	this.inserttime=l.getInserttime();
    	this.updatetime=l.getUpdatetime();
	}

	public Long getId() {
		return id;
	} 

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public ShipmentDaoSample getShipment() {
		return shipment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setShipment(ShipmentDaoSample shipment) {
		this.shipment = shipment;
	}

	public LocalDateTime getInserttime() {
		return inserttime;
	}

	public LocalDateTime getUpdatetime() {
		return updatetime;
	}

	public void setInserttime(LocalDateTime inserttime) {
		this.inserttime = inserttime;
	}

	public void setUpdatetime(LocalDateTime updatetime) {
		this.updatetime = updatetime;
	}
    
    
    
}