package com.tekup.realestateapi.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="visit")

public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id") 
	private User user;
    
    @ManyToOne
    @JoinColumn(name = "realestate_id")
    private RealEstate realEstate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVisite;


}
