package com.tekup.realestateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.Country;
import com.tekup.realestateapi.models.Employee;
import com.tekup.realestateapi.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	  @GetMapping("/list")
	    public List<Employee> getAllCountries() {
	        return employeeService.getEmployee();
	    }

	    @GetMapping("/{id}")
	    public Employee getCountryById(@PathVariable Long id) {
	        return employeeService.getEmployee(id);
	    }

	    @PostMapping("/add")
	    public Employee saveCountry(@RequestBody Employee country) {
	       return  employeeService.createEmployee(country);
	    }

	    @PutMapping("/update/{idemp}/project/{idproj}")
	    public Employee assignPrejctEmp(
	    	@PathVariable Long idemp,
	    	@PathVariable Long idproj
	    )
	    {
	    return employeeService.assignProjectToEmployee(idemp,idproj);	
	    }
}
