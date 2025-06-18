package com.tms.entity;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tms.dto.ShipmentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "shipments")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id"
		)
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Shipment() {
    	shipmentNumber=generateShipmentNumber();
	}
    
    public Shipment(ShipmentDTO s){
    	shipmentNumber=generateShipmentNumber();
    	origin = s.getOrigin();
    	destination = s.getDestination();
    	status = "Driver will be Assigned Shortly";
    	vehicleType = s.getVehicleType();
    	shipmentDate = s.getShipmentDate();
    	weight = s.getWeight();
    }
    
    @OneToOne(mappedBy = "shipment",cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE,
		    CascadeType.REFRESH,
		    CascadeType.DETACH
		})
//    @JsonBackReference
    private Vehicle vehicle;
    
    @OneToOne(mappedBy = "shipment",cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Location location;
    
    @ManyToOne
    @JoinColumn(name="user_id")
//    @JsonIgnore
    private User user;
    
    private double weight;
    private String shipmentNumber;
    private String origin;
    private String destination;
    private String status; // e.g., 'in transit', 'delivered', etc.
    private String vehicleType;
    
    private Date shipmentDate;
    
    // Getters and Setters

	public Long getId() {
		return id;
	}
	
	private String generateShipmentNumber() {
        return "SHIP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getStatus() {
		return status;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", location=" + location + ",  weight=" + weight
				+ ", shipmentNumber=" + shipmentNumber + ", origin=" + origin + ", destination=" + destination
				+ ", status=" + status + ", vehicleType=" + vehicleType + ", shipmentDate=" + shipmentDate + "]";
	}


}
