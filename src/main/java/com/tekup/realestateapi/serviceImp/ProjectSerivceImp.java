package com.tekup.realestateapi.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.Project;
import com.tekup.realestateapi.repository.ProjectRepository;
import com.tekup.realestateapi.service.ProjectService;

@Service
public class ProjectSerivceImp  implements ProjectService{

	@Autowired
	private  ProjectRepository projectRepository;
	@Override
	public void createProject(Project project) {

		projectRepository.save(project);
	}

	@Override
	public List<Project> getProject() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProject(Long id) {
		
		Project project = projectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Project Id:" + id));

        return project;
}

}
