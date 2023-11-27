package com.tekup.realestateapi.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Transient;
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
    private Long id;
    private String description;
    private int NumFloor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
	@Enumerated(EnumType.STRING)
    private EStates states;
	@Enumerated(EnumType.STRING)
	private EState state;

	@OneToMany(mappedBy = "realEstate")
	 private List<RealEstateImage> images;
	  
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id") 
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
	private float price;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "town_id")
	private Town town;
	

		
	
	 
	@Override
	public String toString() {
	    return "RealEstate{" +
	            "id=" + id +
	            ", description='" + description + '\'' +
	            ", numFloor=" + this.NumFloor +
	            ", createdAt=" + createdAt +
	            ", states=" + states +
	            ", state=" + state +
	            ", images=" + images +
	            ", user=" + (user != null ? user.getIdUser() : null) + // Only include user ID
	            ", price=" + price +
	            ", category=" + (category != null ? category.getIdCat() : null) + // Only include category ID
	            '}';
	}

	 
	 
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
