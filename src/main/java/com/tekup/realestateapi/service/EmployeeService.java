package com.tekup.realestateapi.service;

import java.util.List;

import com.tekup.realestateapi.models.Employee;


public interface EmployeeService {
	Employee createEmployee(Employee cat);
	List<Employee> getEmployee();
	Employee getEmployee(Long id);
	Employee assignProjectToEmployee(Long idemp,Long idproj);
  
}
