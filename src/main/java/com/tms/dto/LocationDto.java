package com.tms.dto;

import java.time.LocalDateTime;

public class LocationDto {

	private Double latitude;
    private Double longitude;
    private LocalDateTime inserttime;
    private LocalDateTime updatetime;
	
    public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
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