package com.tms.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;

import com.tms.dto.SignupRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 10, max = 15)
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role; // Example: ADMIN, USER, etc.

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    public User() {
    }

    public User(SignupRequest s) {
        username = s.getUsername();
        password = s.getPassword();
        firstName = s.getFirstname();
        lastName = s.getLastname();
        phoneNumber = s.getPhonenumber();
        email = s.getEmail();
        role = s.getRole();
        isActive = true;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    
    public static UserDetails build(User user) {
    	String role = user.getRole() != null ? user.getRole() : "USER";
        Collection<? extends GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
	
}