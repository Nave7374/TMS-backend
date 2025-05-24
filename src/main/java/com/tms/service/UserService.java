package com.tms.service;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.entity.Shipment;
import com.tms.entity.User;
import com.tms.exception.ResourceNotFoundException;
import com.tms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Autowired
    PasswordEncoder encoder;

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public List<User> fetchAll(){
    	return userRepository.findAll();
    }
    
    public Optional<User> findUserById(Long id){
    	return userRepository.findById(id);
    }

	public List<SHipmentHistoryForUser> getShipmentHistory(Long id) {
		User user = findUserById(id).orElseThrow(()-> new ResourceNotFoundException("Shipment History Not Found"));
		return user.getShipmenthistory().stream().map(i->new SHipmentHistoryForUser(i)).collect(Collectors.toList());
	}

	public void updateByUserUpdateid(Long id, UserUpdate u) {
		User user = userRepository.findById(id).orElse(null);
		user.setEmail(u.getEmail());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setPhoneNumber(u.getPhoneNumber());
		if(!encoder.matches(u.getPassword(), user.getPassword())) {
			user.setPassword(encoder.encode(u.getPassword()));
			System.out.println("Password Updated");
		}
		user.setRole(u.getRole());
		user.setUsername(u.getUsername());
		userRepository.save(user);
	}

	public UserUpdate getUserUpdateById(Long id) {
		User U = findUserById(id).orElse(null);
		return new UserUpdate(U);
	}

	public void deleteUser(Long id) {
		User u = findUserById(id).orElse(null);
		userRepository.delete(u);
	}
    
} 