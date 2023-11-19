package com.tekup.realestateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private UserService userService;

    /**
     * add user
     */

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
    	
        return userService.addUser(user);

    }
    
    /**
     * get users as list
     */

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * get user by id
     */

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }
    
    /**
     * delete user by id
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        
    	return userService.deleteUser(id);

    }

    /**
     * update user 
     */
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id ,@RequestBody User user){
        
    	return userService.updateUser(id,user);

    }
}
