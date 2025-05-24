package com.tms.DAO.Update;

import com.tms.entity.Driver;

public class DriverUpdate {
	
	private Long id;
	
	private String firstname;
	private String lastname;
	private String phone;
	private String gender;
	private Integer age;
	private String email;
	private String username;
	private String password;

	public DriverUpdate() {
		// TODO Auto-generated constructor stub
	}
	
	public DriverUpdate(Driver d) {
		this.id=d.getId();
		this.age=d.getAge();
		this.email=d.getEmail();
		this.firstname=d.getFirstname();
		this.lastname=d.getLastname();
		this.gender=d.getGender();
		this.password=d.getPassword();
		this.phone=d.getPhone();
		this.username=d.getUsername();
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
	
}