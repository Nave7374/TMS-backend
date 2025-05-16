package com.tms.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tms.dto.DriverDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id"
		)
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	private String lastname;
	private String phone;
	private String gender;
	private Integer age;
	private String email;
	private String username;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_id")
//	@JsonManagedReference
	private Vehicle vehicle;
	
	@OneToMany(mappedBy = "driver" , cascade = CascadeType.ALL)
	private List<ShipmentHistory> shipments = new ArrayList<ShipmentHistory>();
	
	public Driver() {
	}

	public Driver(DriverDTO d) {
		setFirstname(d.getFirstname());
		setLastname(d.getLastname());
		setEmail(d.getEmail());
		setGender(d.getGender());
		setPassword(d.getPassword());
		setPhone(d.getPhone());
		setUsername(d.getUsername());
		setAge(d.getAge());
	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPhone() {
		return phone;
	}

	public String getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<ShipmentHistory> getShipments() {
		return shipments;
	}

	public void setShipments(ShipmentHistory shipments) {
		this.shipments.add(shipments);
	}
	
}