package com.tms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tms.dto.LocationDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double latitude;
    private double longitude;
    
    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Vehicle vehicle;

    // Constructors
    public Location() {
    	
    }

    public Location(LocationDto l) {
    	latitude= l.getLatitude();
    	longitude = l.getLatitude();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}