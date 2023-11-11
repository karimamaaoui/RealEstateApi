package com.tekup.realestateapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue
    @Column(name = "idCat")
	private Integer idCat;
	private String title;
	
   
}
