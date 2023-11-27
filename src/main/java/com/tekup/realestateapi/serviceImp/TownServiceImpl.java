package com.tekup.realestateapi.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.Country;
import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.models.TownWithCountryDTO;
import com.tekup.realestateapi.repository.CountryRepository;
import com.tekup.realestateapi.repository.TownRepository;
import com.tekup.realestateapi.service.TownService;

import jakarta.persistence.Tuple;

@Service
public class TownServiceImpl implements TownService {

	@Autowired
	private TownRepository townRepository;
	
	@Autowired
	private CountryRepository countryRepository;

		 @Override
	    public ResponseEntity<List<Town>> getAllTowns() {
	        List<Town> towns = townRepository.findAll();
	        return new ResponseEntity<>(towns, HttpStatus.OK);
	    }

	    @Override
	    public ResponseEntity<Town> getTownById(Long id) {
	        Town town = townRepository.findById(id).orElse(null);
	        if (town != null) {
	            return new ResponseEntity<>(town, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @Override
	    public ResponseEntity<Town> saveTown(Town town) {
	        Town savedTown = townRepository.save(town);
	        return new ResponseEntity<>(savedTown, HttpStatus.CREATED);
	    }

	    @Override
	    public ResponseEntity<Void> deleteTown(Long id) {
	        if (townRepository.existsById(id)) {
	            townRepository.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @Override
	    public ResponseEntity<Town> updateTown(Long id, Town updatedTown) {
	        Town existingTown = townRepository.findById(id).orElse(null);

	        if (existingTown != null) {
	            // Update the properties of the existing town with the new values
	            existingTown.setName(updatedTown.getName());

	            // Save the updated town
	            Town savedTown = townRepository.save(existingTown);
	            return new ResponseEntity<>(savedTown, HttpStatus.OK);
	        } else {
	            // Town not found
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
		@Override
		public ResponseEntity<Town> addCountryToTown(Long idtown, Long idcountry) {
			Set<Country> countrySet=null;
			Town town=townRepository.findById(idtown).get();
			Country country=countryRepository.findById(idcountry).get();
			countrySet=town.getCountriesTown();
			countrySet.add(country);
			town.setCountriesTown(countrySet);		
			Town savedTown=townRepository.save(town);
	        return new ResponseEntity<>(savedTown, HttpStatus.CREATED);


		}
		
		@Override
		public ResponseEntity<Town> updateCountryInTown(Long idtown, Long idcountry) {
		    Town town = townRepository.findById(idtown).orElse(null);
		    Country country = countryRepository.findById(idcountry).orElse(null);

		    if (town != null && country != null) {
		        // Update the country in the town
		        town.getCountriesTown().clear();  // Clear existing relationships
		        town.getCountriesTown().add(country);  // Add the new relationship

		        // Save the updated town
		        Town savedTown = townRepository.save(town);
		        return new ResponseEntity<>(savedTown, HttpStatus.OK);
		    } else {
		        // Town or Country not found
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		}


	}
