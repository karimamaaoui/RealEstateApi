package com.tekup.realestateapi.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name="employee")
public class Employee {

	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idemp;
	    private String name;

	    @ManyToMany
	    @JoinTable(name="employee_project",
	    joinColumns = @JoinColumn(name ="idemp"),
	    inverseJoinColumns = @JoinColumn(name="id_proj")
	    )
	    private Set<Project> projectsEmployee=new HashSet<>();
	
}
