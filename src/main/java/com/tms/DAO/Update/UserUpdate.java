package com.tms.DAO.Update;

import com.tms.entity.User;

public class UserUpdate {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String role;
    
    public UserUpdate(User u) {
    	this.id=u.getId();
    	this.email=u.getEmail();
    	this.firstName=u.getFirstName();
    	this.lastName=u.getLastName();
    	this.password=u.getPassword();
    	this.phoneNumber=u.getPhoneNumber();
    	this.role=u.getRole();
    	this.username=u.getUsername();
	}
    
    public UserUpdate() {
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
