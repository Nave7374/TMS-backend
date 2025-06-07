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
   	public List<User> getAllUsers(){
	   return userEntityService.fetchAll();
   	}
   
   	@GetMapping("/page/{pageno}")
   	public PaginationDto<User> getMethodName(@PathVariable Integer pageno) {
	   return userEntityService.findbypage(pageno);
   	}
   
   	@PostMapping("/register")
   	public User registerUser(@RequestBody User user) {
	   return userEntityService.saveUser(user);
   	}

   	@GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
       return userEntityService.findUserByUsername(username).orElse(null);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userEntityService.findUserByEmail(email).orElse(null);
    }
    
    @GetMapping("/shipmenthistory/{id}")
    public List<SHipmentHistoryForUser> getUsersShipmentHistory(@PathVariable Long id){
    	return userEntityService.getShipmentHistory(id);
    }
    
    @PutMapping("/update/{id}")
    public User updatetheUserByID(@PathVariable Long id,@RequestBody UserUpdate user) {
    	return userEntityService.updateByUserUpdateid(id,user);
    }
    
    @GetMapping("/update/{id}")
    public UserUpdate getUserById(@PathVariable Long id) {
    	return userEntityService.getUserUpdateById(id); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
    	return userEntityService.deleteUser(id);
    }
    
}