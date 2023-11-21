package com.tekup.realestateapi.serviceImp;

import java.util.List;
import java.util.Optional;

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
	private TownRepository repository;
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public ResponseEntity<?> addTown(Town town) {
	     try {
	            Town savedTown = repository.save(town);
	            return ResponseEntity.ok(savedTown);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Error creating town: " + e.getMessage());
	        }
	    }
  
	
	

	@Override
	public ResponseEntity<List<Town>> getTowns() {
	    List<Town> towns = repository.findAll();

	    if (towns.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(towns, HttpStatus.OK);
	    }
	}

	public Optional<TownWithCountryDTO> getTownWithCountryById(Long id) {
	    Optional<Tuple> result = repository.findTownWithCountryDTOById(id);
	    return result.map(tuple -> {
	        Long townId = tuple.get("idTown", Long.class);
	        String townName = tuple.get("name", String.class);
	        Long countryId = tuple.get("countryId", Long.class);
	        String countryName = tuple.get("countryName", String.class);

	        return new TownWithCountryDTO(townId, townName, countryId, countryName);
	    });
	}

	
	
	@Override
	public ResponseEntity<?> getTown(Long id) {
	    try {
	        Optional<Town> optionalTown = repository.findById(id);
	        System.out.println("town is " + optionalTown);
	        if (optionalTown.isPresent()) {
	            Town town = optionalTown.get();
	            System.out.println("town is " + town);

	            return ResponseEntity.ok(town);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Town not found with ID: " + id);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving town: " + e.getMessage());
	    }
	}

	@Override
	public ResponseEntity<?> deleteTown(Long id) {
		try {

	        Town town = repository
	                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid town Id:" + id));

	        repository.delete(town);	
	        
	        return ResponseEntity.status(HttpStatus.OK).body("town deleted successfully");
		}
	    catch (RuntimeException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("town not found: " + e.getMessage());
	    }
	  
	}

	@Override
	public ResponseEntity<?> updateTown(Long id, Town town) {
	    Optional<Town> optionalTown = repository.findById(id);

        if (optionalTown.isPresent()) {
            Town existingTown = optionalTown.get();

            // Update the properties of the existing town with the values from updatedTown
            existingTown.setName(town.getName());
            // Update other properties as needed

            // Save the updated town back to the database
            repository.save(existingTown);

            return new ResponseEntity<>("town updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("town not found", HttpStatus.NOT_FOUND);
        }
    }

	@Override
	public ResponseEntity<?> createTown(String name, Long idCountry) {
	    try {
	        if (name == null) {
	            return ResponseEntity.badRequest().body("Town name cannot be null");
	        }

	        Optional<Country> countryOptional = countryRepository.findById(idCountry);

	        if (countryOptional.isPresent()) {
	            Country country = countryOptional.get();

	            // Create a new Town instance
	            Town newTown = new Town();
	            newTown.setName(name);
	            newTown.setCountry(country);

	            // Save the new Town to the database
	            Town savedTown = repository.save(newTown);

	            return new ResponseEntity<>(savedTown, HttpStatus.CREATED);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country not found with ID: " + idCountry);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Town: " + e.getMessage());
	    }
	}


	@Override
	public List<TownWithCountryDTO> getTownsWithCountries() {

		 List<TownWithCountryDTO> towns = repository.findAllWithCountries();
	        
	        for (TownWithCountryDTO town : towns) {
	            System.out.println("town name: " + town.getName()+" town Country id : "+town.getCountryId()+" town Country name : "+town.getCountryName() );
	        }

	        return towns;
	}


}
