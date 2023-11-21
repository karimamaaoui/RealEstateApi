package com.tekup.realestateapi.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name="real_estate")
public class RealEstate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_real")
	private Long idReal;
    private String description;
    private int NumFloor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
	@Enumerated(EnumType.STRING)
    private EStates states;
	@Enumerated(EnumType.STRING)
	private EStates state;
	@OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
	private List<Images> images;
	@ManyToOne
	@JoinColumn(name = "user_id") 
	private User user;
	private float price;
	/* @ManyToOne
	 @JoinColumn(name = "town_id")
	 private Town town;
*/
	 @OneToOne
	 @JoinColumn(name = "category_id")
	 private Category category;

	 
	 
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
