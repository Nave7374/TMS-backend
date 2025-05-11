package com.tms.dto;

public class SignupRequest {
	
	private String firstname;
	private String lastname;
	private String phonenumber;
    private String username;
    private String email;
    private String password;
    private String role; // Optional: "USER", "ADMIN", etc.
    
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
    
    
}
