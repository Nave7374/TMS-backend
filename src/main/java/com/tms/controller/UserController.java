package com.tms.controller;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.entity.User;
import com.tms.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping
   public List<User> getAllUsers(){
	   return userService.fetchAll();
   }
    
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username).orElse(null);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email).orElse(null);
    }
    
    @GetMapping("/shipmenthistory/{id}")
    public List<SHipmentHistoryForUser> getUsersShipmentHistory(@PathVariable Long id){
    	return userService.getShipmentHistory(id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatetheUserByID(@PathVariable Long id,@RequestBody UserUpdate user) {
    	userService.updateByUserUpdateid(id,user);
    	return ResponseEntity.ok("Updated Successfully");
    }
    
    @GetMapping("/update/{id}")
    public UserUpdate getUserById(@PathVariable Long id) {
    	return userService.getUserUpdateById(id); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
    	userService.deleteUser(id);
    	return ResponseEntity.ok("User Deleted");
    }
}