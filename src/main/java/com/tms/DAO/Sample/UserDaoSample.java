package com.tms.DAO.Sample;

import com.tms.entity.User;

public class UserDaoSample {

	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String role;
	
	public UserDaoSample(User u) {
		setEmail(u.getEmail());
		setFirstName(u.getFirstName());
		setId(u.getId());
		setLastName(u.getLastName());
		setPassword(u.getPassword());
		setPhoneNumber(u.getPhoneNumber());
		setRole(u.getRole());
		setUsername(u.getUsername());
	}
	
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(String role) {
		this.role = role;
	}
}