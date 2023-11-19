package com.tekup.realestateapi.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	 @ManyToOne
	 @JoinColumn(name = "country_id")
	 private Country country;

	 
	 @OneToMany(mappedBy = "town", cascade = CascadeType.ALL)
	 private List<RealEstate> realEstates;

	 
	 
	 public void addRealEstate(RealEstate realEstate) {
	        if (realEstates == null) {
	            realEstates = new ArrayList<>();
	        }
	        realEstates.add(realEstate);
	      //  realEstate.setTown(this);
	    }

	    public void removeRealEstate(RealEstate realEstate) {
	        if (realEstates != null) {
	            realEstates.remove(realEstate);
	          //  realEstate.setTown(null);
	        }
	    }
}
