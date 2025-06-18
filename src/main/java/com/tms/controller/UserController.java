package com.tms.controller;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.dto.PaginationDto;
import com.tms.entity.User;
import com.tms.service.interfaces.UserEntityService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000","https://transportmanagementsys.netlify.app"},allowedHeaders = "*")
public class UserController {

	@Autowired
    private UserEntityService userEntityService;
    
	@GetMapping
   	public ResponseEntity<List<User>> getAllUsers(){
	   return userEntityService.fetchAll();
   	}
   
   	@GetMapping("/page/{pageno}")
   	public ResponseEntity<PaginationDto<User>> getMethodName(@PathVariable Integer pageno) {
	   return userEntityService.findbypage(pageno);
   	}
   	
   	@GetMapping("/filterbyname")
   	public ResponseEntity<List<User>> getAllUsersFilterByName(){
   		return userEntityService.fetchAllFilterByName();
   	}
   
   	@PostMapping("/register")
   	public ResponseEntity<User> registerUser(@RequestBody User user) {
	   return userEntityService.saveUser(user);
   	}

   	@GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
       return userEntityService.findUserByUsername(username);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userEntityService.findUserByEmail(email);
    }
    
    @GetMapping("/shipmenthistory/{id}")
    public ResponseEntity<List<SHipmentHistoryForUser>> getUsersShipmentHistory(@PathVariable Long id){
    	return userEntityService.getShipmentHistory(id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatetheUserByID(@PathVariable Long id,@RequestBody UserUpdate user) {
    	return userEntityService.updateByUserUpdateid(id,user);
    }
    
    @GetMapping("/update/{id}")
    public ResponseEntity<UserUpdate> getUserById(@PathVariable Long id) {
    	return userEntityService.getUserUpdateById(id); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
    	return userEntityService.deleteUser(id);
    }
    
}