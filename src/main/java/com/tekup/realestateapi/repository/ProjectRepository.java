package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long > {

}
