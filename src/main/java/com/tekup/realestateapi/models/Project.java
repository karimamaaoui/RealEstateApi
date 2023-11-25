package com.tekup.realestateapi.models;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="project")
public class Project {

	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idProj;
	    private String name;

	    @JsonIgnore
	    @ManyToMany(mappedBy = "projectsEmployee")
	    private Set<Employee> employeeProject=new HashSet<>();
	
}
