package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.realestateapi.models.User;


public interface UserService {
	ResponseEntity<?> addUser(User user);
    List<User> getUsers();
    ResponseEntity<?>  getUser(Integer id);
    ResponseEntity<?> deleteUser(Integer id);   
    ResponseEntity<?> createUser(User user);
    ResponseEntity<?> updateUser(Integer id, User user);

    public void activateUser(String email);
}