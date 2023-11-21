package com.tekup.realestateapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.models.TownWithCountryDTO;

public interface TownService {
	ResponseEntity<?> addTown(Town town) ;
	ResponseEntity<List<Town>> getTowns();
    ResponseEntity<?>  getTown(Long id);
    ResponseEntity<?> deleteTown(Long id);   
    ResponseEntity<?> updateTown(Long id, Town town);
    
	ResponseEntity<?> createTown(String name,Long idCountry);
    List<TownWithCountryDTO> getTownsWithCountries() ;
    Optional<TownWithCountryDTO> getTownWithCountryById(Long id);


}
