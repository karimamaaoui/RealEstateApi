package com.tekup.realestateapi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getUser() {
		System.out.println("getting users");
		return "Users";
	}
}
