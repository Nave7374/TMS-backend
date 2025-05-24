package com.tms.DAO.Sample;

import com.tms.entity.Location;

public class LocationDaoSample {

	private Long id;
    
    private double latitude;
    private double longitude;
	
    public LocationDaoSample(Location l) {
    	
    	this.id=l.getId();
    	this.longitude=l.getLongitude();
    	this.latitude=l.getLatitude();
    	
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
    
}
