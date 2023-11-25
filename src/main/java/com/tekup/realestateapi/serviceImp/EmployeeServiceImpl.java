package com.tekup.realestateapi.serviceImp;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.Category;
import com.tekup.realestateapi.models.Employee;
import com.tekup.realestateapi.models.Project;
import com.tekup.realestateapi.repository.EmployeeRepository;
import com.tekup.realestateapi.repository.ProjectRepository;
import com.tekup.realestateapi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public Employee createEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	@Override
	public List<Employee> getEmployee() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Long id) {
	    Employee emp = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Employee Id:" + id));

        return emp;

	}

	@Override
	public Employee assignProjectToEmployee(Long idemp, Long idproj) {
		Set<Project> projectSet=null;
		Employee employee=employeeRepository.findById(idemp).get();
		Project project=projectRepository.findById(idproj).get();
		projectSet=employee.getProjectsEmployee();
		projectSet.add(project);
		employee.setProjectsEmployee(projectSet);		
		return 	employeeRepository.save(employee);
	}
	

}
