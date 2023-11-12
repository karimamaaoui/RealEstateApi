package com.tekup.realestateapi.service;

import java.util.List;

import com.tekup.realestateapi.models.User;


public interface UserService {
    void addUser(User user);
    List<User> getUsers();
    User getUser(Integer id);
    void deleteUser(Integer id);
    
    User createUser(User user);
    public void activateUser(String email);
}