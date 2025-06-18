package com.tms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tms.dto.LocationDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "locations")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id"
		)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double latitude;
    private Double longitude;
    
    @CreationTimestamp
    @Column(updatable = false)
	private LocalDateTime inserttime;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatetime;
    
	@OneToOne
    @JoinColumn(name="shipment_id")
//    @JsonBackReference
    private Shipment shipment;
    
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

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
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