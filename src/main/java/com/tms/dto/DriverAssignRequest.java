package com.tms.dto;

public class DriverAssignRequest {

	private long driverID;
	private long vehicleID;
	
	public long getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}
	public long getDriverID() {
		return driverID;
	}
	public void setDriverID(long driverID) {
		this.driverID = driverID;
	}
	
}
