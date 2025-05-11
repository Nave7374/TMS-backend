package com.tms.dto;

public class AssignRequest {

	private long shipmentID;
	private long vehicleID;
	
	public long getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}
	public long getShipmentID() {
		return shipmentID;
	}
	public void setShipmentID(long shipmentID) {
		this.shipmentID = shipmentID;
	}
	
}