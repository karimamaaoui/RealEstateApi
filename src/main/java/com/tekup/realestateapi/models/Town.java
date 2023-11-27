package com.tekup.realestateapi.models;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name="town")
public class Town {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_town")
	private Long  idTown;
	private String name;
	
	 	@ManyToMany
	    @JoinTable(name="town_country",
	    joinColumns = @JoinColumn(name ="id_town"),
	    inverseJoinColumns = @JoinColumn(name="id_country")
	    )
	private Set<Country> countriesTown=new HashSet<>();
	
		@JsonIgnore
		@OneToMany(mappedBy = "town")
		    private Set<RealEstate> realEstates;
	 
		public Town(Long id) {
	        this.idTown = id;
	    }
	  
}
