package com.tms.DAO.Sample;

import java.time.LocalDateTime;

import com.tms.entity.Location;

public class LocationDaoSample {

	private Long id;
    
    private double latitude;
    private double longitude;
    private LocalDateTime inserttime;
    private LocalDateTime updatetime;
    
    public LocationDaoSample(Location l) {
    	
    	this.id=l.getId();
    	this.longitude=l.getLongitude();
    	this.latitude=l.getLatitude();
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
