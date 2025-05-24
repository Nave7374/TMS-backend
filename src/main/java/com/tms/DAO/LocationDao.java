package com.tms.DAO;

import com.tms.DAO.Sample.ShipmentDaoSample;
import com.tms.entity.Location;

public class LocationDao {
	
	private Long id;
    
    private double latitude;
    private double longitude;
    private ShipmentDaoSample shipment;
    
    public LocationDao(Location l) {
    	this.id=l.getId();
    	this.longitude=l.getLongitude();
    	this.latitude=l.getLatitude();
    	if(l.getShipment()!=null)this.shipment=new ShipmentDaoSample(l.getShipment());
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
    
    
    
}