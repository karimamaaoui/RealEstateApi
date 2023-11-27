package com.tekup.realestateapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.models.TownWithCountryDTO;

public interface TownService {

	 	ResponseEntity<List<Town>> getAllTowns();
	    ResponseEntity<Town> getTownById(Long id);
	    ResponseEntity<Town> saveTown(Town town);
	    ResponseEntity<Void> deleteTown(Long id);
	    ResponseEntity<Town> addCountryToTown(Long idtown,Long idcountry);
	    ResponseEntity<Town> updateCountryInTown(Long idtown, Long idcountry);
	    ResponseEntity<Town> updateTown(Long id, Town updatedTown);

}
