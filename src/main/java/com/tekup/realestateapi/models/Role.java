package com.tekup.realestateapi.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
	private Long  idRole;
	@Enumerated(EnumType.STRING)
    private ERole name = ERole.CLIENT;
	
	/*@OneToMany(mappedBy = "role_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();
	 */
}
