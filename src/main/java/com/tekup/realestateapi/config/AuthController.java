package com.tekup.realestateapi.config;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.config.EmailVerification.EmailSender;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.service.UserService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSender emailSender;
	
	private Logger logger=LoggerFactory.getLogger(AuthController.class);
	
	
	
	  @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getEmail(), request.getPassword());
	    
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);
	        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

	        List<String> roleNames = null;
	        if (authorities != null) {
	            roleNames = authorities
	                .stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());
	        }

	       
	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername())
	                .roles(roleNames)
	                .build();
	        
				        
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  
	 
	  
	  private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	        	authenticationManager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }
	    
	    
	    @PostMapping("register")
	    public User createUser(@RequestBody User user)
	    {
	    	
	    	return userService.createUser(user);
	    }
	   
	    @GetMapping("/activate")
	    public ResponseEntity<String> activateUser(@RequestParam String email) {
	        userService.activateUser(email);
	        return new ResponseEntity<>("User activated successfully", HttpStatus.OK);
	    }
}
