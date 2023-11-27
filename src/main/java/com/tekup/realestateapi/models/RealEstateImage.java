package com.tekup.realestateapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "real_estate_image")
public class RealEstateImage {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
	private Long  idImage;
	private String imageName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "real_estate_id")
	private RealEstate realEstate;
	 
	 
		
}
