package com.tekup.realestateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.Employee;
import com.tekup.realestateapi.models.Project;
import com.tekup.realestateapi.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	  @GetMapping("/list")
	    public List<Project> getAllProjects() {
	        return projectService.getProject();
	    }

	    @GetMapping("/{id}")
	    public Project getCountryById(@PathVariable Long id) {
	        return projectService.getProject(id);
	    }

	    @PostMapping("/add")
	    public void saveProject(@RequestBody Project project) {
	    	projectService.createProject(project);
	    }

}
