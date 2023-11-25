package com.tekup.realestateapi.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tekup.realestateapi.models.Country;
import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.repository.CountryRepository;
import com.tekup.realestateapi.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	
	@Autowired
	private CountryRepository countryRepository;
		 
	@Override
	    public ResponseEntity<List<Country>> getAllCountries() {
	        List<Country> countries = countryRepository.findAll();
	        return new ResponseEntity<>(countries, HttpStatus.OK);
	    }

	    @Override
	    public ResponseEntity<Country> getCountryById(Long id) {
	        Country country = countryRepository.findById(id).orElse(null);
	        if (country != null) {
	            return new ResponseEntity<>(country, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @Override
	    public ResponseEntity<Country> saveCountry(Country country) {
	        Country savedCountry = countryRepository.save(country);
	        return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
	    }

	    @Override
	    public ResponseEntity<?> deleteCountry(Long id) {
	        if (countryRepository.existsById(id)) {
	            countryRepository.deleteById(id);
	            return new ResponseEntity<>("Country with ID " + id + " deleted successfully.", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Country with ID " + id + " not found.", HttpStatus.NOT_FOUND);
	        }
	    }

		@Override
		public ResponseEntity<Country> updateCountry(Long id, Country updatedCountry) {
			
		     Country existingCountry = countryRepository.findById(id).orElse(null);

		        if (existingCountry != null) {
		            // Update the properties of the existing town with the new values
		        	existingCountry.setName(updatedCountry.getName());

		            // Save the updated town
		            Country savedCountry = countryRepository.save(existingCountry);
		            return new ResponseEntity<>(savedCountry, HttpStatus.OK);
		        } else {
		            // Country not found
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
}
	}
