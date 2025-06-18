package com.tms.service;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.dto.PaginationDto;
import com.tms.entity.User;
import com.tms.exception.UserIdNotFound;
import com.tms.repository.UserRepository;
import com.tms.service.interfaces.UserEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<User> findUserByUsername(String username) {
        return ResponseEntity.ok(userRepository.findByUsername(username).orElseThrow(()-> new UserIdNotFound("Username not found")));
    }

    @Override
    public ResponseEntity<User> findUserByEmail(String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email).orElseThrow(()-> new UserIdNotFound("User Email not found")));
    }

    @Override
    public ResponseEntity<User> saveUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
    
    @Override
    public ResponseEntity<List<User>> fetchAll(){
    	return ResponseEntity.ok(userRepository.findAll());
    }
    
    @Override
    public Optional<User> findUserById(Long id){
    	return userRepository.findById(id);
    }
    
    @Override
	public ResponseEntity<List<SHipmentHistoryForUser>> getShipmentHistory(Long id) {
		User user = findUserById(id).orElseThrow(()-> new UserIdNotFound("Shipment History Not Found"));
		return ResponseEntity.ok(user.getShipmenthistory().stream().map(i->new SHipmentHistoryForUser(i)).collect(Collectors.toList()));
	}

    @Override
	public ResponseEntity<User> updateByUserUpdateid(Long id, UserUpdate u) {
		User user = userRepository.findById(id).orElseThrow(()-> new UserIdNotFound("User ID not found"));
		user.setEmail(u.getEmail());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setPhoneNumber(u.getPhoneNumber());
		user.setRole(u.getRole());
		user.setUsername(u.getUsername());
		userRepository.save(user);
//		System.out.println(u);
		return ResponseEntity.ok(user);
		
	}

    @Override
	public ResponseEntity<UserUpdate> getUserUpdateById(Long id) {
		User U = findUserById(id).orElseThrow(()-> new UserIdNotFound("User ID not found"));
		return ResponseEntity.ok(new UserUpdate(U));
	}

    @Override
	public ResponseEntity<?> deleteUser(Long id) {
		User u = findUserById(id).orElseThrow(()-> new UserIdNotFound("User ID not found"));
		if(!u.getShipment().isEmpty())return ResponseEntity.badRequest().body("User Has Shipments cannot delete User");
		userRepository.delete(u);
		return ResponseEntity.ok("User Deleted");
	}

    @Override
	public ResponseEntity<PaginationDto<User>> findbypage(Integer pageno) {
		return ResponseEntity.ok(new PaginationDto<User>(userRepository.findAll(PageRequest.of(pageno-1, 2))));
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Optional<User> findUserByUsernameOptional(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findUserByEmailOptional(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public ResponseEntity<List<User>> fetchAllFilterByName() {
		List<User> users  = userRepository.findAll(Sort.by("firstName"));
		return ResponseEntity.ok(users);
	}
}