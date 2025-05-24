package com.tms.DAO.Sample;

import java.util.List;

import com.tms.entity.Driver;
import com.tms.entity.ShipmentHistory;

public class DriverSampleDto {

private Long id;
	
	private String firstname;
	private String lastname;
	private String phone;
	private String gender;
	private Integer age;
	private String email;
	private String username;
	private String password;
	
	private List<ShipmentHistory> shipments;
	
	public DriverSampleDto(Driver d) {
		setId(d.getId());
		setAge(d.getAge());
		setEmail(d.getEmail());
		setFirstname(d.getFirstname());
		setGender(d.getGender());
		setLastname(d.getLastname());
		setPassword(d.getPassword());
		setPhone(d.getPhone());
		setShipments(d.getShipments());
		setUsername(d.getUsername());
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

	public List<ShipmentHistory> getShipments() {
		return shipments;
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

	public void setShipments(List<ShipmentHistory> shipments) {
		this.shipments = shipments;
	}
	
}
