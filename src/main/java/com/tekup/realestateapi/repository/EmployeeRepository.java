package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long > {

}
