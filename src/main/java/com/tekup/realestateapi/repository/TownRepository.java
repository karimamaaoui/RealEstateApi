package com.tekup.realestateapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tekup.realestateapi.models.TownWithCountryDTO;

import jakarta.persistence.Tuple;

import com.tekup.realestateapi.models.Town;

public interface TownRepository extends JpaRepository<Town, Long>{
	  @Query("SELECT new com.tekup.realestateapi.models.TownWithCountryDTO(t.idTown, t.name, t.country.idCountry, t.country.name) FROM Town t")
	    List<TownWithCountryDTO> findAllWithCountries();

	  @Query(value = "SELECT t.id_town as idTown, t.name, c.id_country as countryId, c.name as countryName " +
	            "FROM town t " +
	            "JOIN country c ON t.country_id = c.id_country " +
	            "WHERE t.id_town = :id", nativeQuery = true)
	    Optional<Tuple> findTownWithCountryDTOById(Long id);
}
