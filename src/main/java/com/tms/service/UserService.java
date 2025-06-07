package com.tms.service;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.dto.PaginationDto;
import com.tms.entity.User;
import com.tms.exception.ResourceNotFoundException;
import com.tms.repository.UserRepository;
import com.tms.service.interfaces.UserEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserEntityService {

	@Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder encoder;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public List<User> fetchAll(){
    	return userRepository.findAll();
    }
    
    @Override
    public Optional<User> findUserById(Long id){
    	return userRepository.findById(id);
    }
    
    @Override
	public List<SHipmentHistoryForUser> getShipmentHistory(Long id) {
		User user = findUserById(id).orElseThrow(()-> new ResourceNotFoundException("Shipment History Not Found"));
		return user.getShipmenthistory().stream().map(i->new SHipmentHistoryForUser(i)).collect(Collectors.toList());
	}

    @Override
	public User updateByUserUpdateid(Long id, UserUpdate u) {
		User user = userRepository.findById(id).orElse(null);
		user.setEmail(u.getEmail());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setPhoneNumber(u.getPhoneNumber());
		user.setRole(u.getRole());
		user.setUsername(u.getUsername());
		userRepository.save(user);
//		System.out.println(u);
		return user;
		
	}

    @Override
	public UserUpdate getUserUpdateById(Long id) {
		User U = findUserById(id).orElse(null);
		return new UserUpdate(U);
	}

    @Override
	public ResponseEntity<?> deleteUser(Long id) {
		User u = findUserById(id).orElse(null);
		if(u.getShipment()!=null) {
			if(!u.getShipment().isEmpty())return ResponseEntity.badRequest().body("User Has Shipments cannot delete User");
		}
		userRepository.delete(u);
		return ResponseEntity.ok("User Deleted");
	}

    @Override
	public PaginationDto<User> findbypage(Integer pageno) {
		return new PaginationDto<User>(userRepository.findAll(PageRequest.of(pageno-1, 2)));
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}