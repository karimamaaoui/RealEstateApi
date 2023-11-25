package com.tekup.realestateapi.service;

import java.util.List;

import com.tekup.realestateapi.models.Project;

public interface ProjectService {
	void createProject(Project cat);
	List<Project> getProject();
	Project getProject(Long id);
  
}
