/*package com.tekup.realestateapi.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = "towns")
@Entity
@Table(name="country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
	private Long  idCountry;
	private String name;
	
	/*@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Town> towns;
	@ManyToMany
    @JoinTable(
        name = "town_country",
        joinColumns = @JoinColumn(name = "id_country"),
        inverseJoinColumns = @JoinColumn(name = "id_town"))
    private Set<Town> towns;
	
}*/
package com.tekup.realestateapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private Long idCountry;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "countriesTown", cascade = CascadeType.ALL)
    private Set<Town> townCountry=new HashSet<>();
    
    
}
