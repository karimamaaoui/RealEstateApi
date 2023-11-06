package com.tekup.realestateapi.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.ERole;
import com.tekup.realestateapi.models.Role;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.repository.RoleRepository;
import com.tekup.realestateapi.repository.UserRepository;
import com.tekup.realestateapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    /**
     * add user
     */
    @Override
    public void addUser(User user) {
       // Role defaultRole = roleRepository.findById(Long.valueOf(1)).orElse(null);
       // user.setRoles(defaultRole);
        userRepository.save(user);
    }

    /**
     * get users as list
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * get user by id
     */

    @Override
    public User getUser(Integer id) {
//        check weather the user is in database or not
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        return user;
    }

    @Override
    public void deleteUser(Integer id) {
//        check weather the user is in database or not
        User user = userRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        userRepository.delete(user);
    }

	public User findByEmail(String email) {
		  User user = userRepository
	                .findByEmail(email)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user email:" + email));

	        return user;
	}

	@Override
	public User createUser(User user) {
	   /*Role userRole = roleRepository.findByName(ERole.CLIENT)
	            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    
	    user.setRole_id(userRole);
	    */
		  Set<Role> roles = new HashSet<>();
		    Role userRole = roleRepository.findByName(ERole.CLIENT)
		            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		        roles.add(userRole);
		        user.setRoles(roles);
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    return userRepository.save(user);
	}


}