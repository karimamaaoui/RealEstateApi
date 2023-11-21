package com.tekup.realestateapi.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.Country;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.repository.CountryRepository;
import com.tekup.realestateapi.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	
	@Autowired
	private CountryRepository countryRepository;
	
	 /**
     * add country
     */
	
	@Override
    public ResponseEntity<?> addCountry(Country newCountry) {
        try {
            // Save the new country to the database
            Country savedCountry = countryRepository.save(newCountry);

            return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions, e.g., constraint violations
            return new ResponseEntity<>("Error creating country: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	 /**
     * get list of countries
     */
	  @Override
	  public ResponseEntity<List<Country>> getCountries() {
		    List<Country> countries = countryRepository.findAll();

		    if (countries.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } else {
		        return new ResponseEntity<>(countries, HttpStatus.OK);
		    }
		}
/**
 *    
	  @Override
	    public ResponseEntity<?> getCountry(Long id) {
	        try {
	            Country country = countryRepository
	                    .findById(id)
	                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid country Id:" + id));

	            CountryDTO countryDTO = convertToDTO(country);
	            return ResponseEntity.status(HttpStatus.CREATED).body(countryDTO);
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("country not found: " + e.getMessage());
	        }
	    }
	  private CountryDTO convertToDTO(Country country) {
	        // Implement the conversion logic based on your requirements
	        CountryDTO countryDTO = new CountryDTO();
	        countryDTO.setIdCountry(country.getIdCountry());
	        countryDTO.setName(country.getName());
	        // Set other fields as needed
	        return countryDTO;
	    }
 
	  
	  /**
	     * get country by id
	   */
	  @Override
	    public ResponseEntity<?> getCountry(Long id) {
	    	try {
	        Country country = countryRepository
	                .findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid country Id:" + id));

	        return ResponseEntity.status(HttpStatus.CREATED).body(country);
	    	} catch (RuntimeException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("country not found: " + e.getMessage());
	        }
	    }

	
	/**
     * delete country by id
     */
	@Override
	public ResponseEntity<?> deleteCountry(Long id) {
		try {

	        Country country = countryRepository
	                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

	        countryRepository.delete(country);	
	        
	        return ResponseEntity.status(HttpStatus.OK).body("Country deleted successfully");
		}
	    catch (RuntimeException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Country not found: " + e.getMessage());
	    }
	  
	
	}

	/**
     * update country by id
     */
	 @Override
	    public ResponseEntity<?> updateCountry(Long id, Country updatedCountry) {
	        Optional<Country> optionalCountry = countryRepository.findById(id);

	        if (optionalCountry.isPresent()) {
	            Country existingCountry = optionalCountry.get();

	            // Update the properties of the existing country with the values from updatedCountry
	            existingCountry.setName(updatedCountry.getName());
	            // Update other properties as needed

	            // Save the updated country back to the database
	            countryRepository.save(existingCountry);

	            return new ResponseEntity<>("Country updated successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @Override
	    public ResponseEntity<List<Country>> getCountriesWithTowns() {
	        System.out.println("hello from countries ");
		 
	        List<Country> countries = countryRepository.findAll();
	        System.out.println("all countries "+countries);
	        return new ResponseEntity<>(countries, HttpStatus.OK);
	    }

}
