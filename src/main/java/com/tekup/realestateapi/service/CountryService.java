package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Country;
import com.tekup.realestateapi.models.Town;


public interface CountryService {



	  	ResponseEntity<List<Country>> getAllCountries();
	    ResponseEntity<Country> getCountryById(Long id);
	    ResponseEntity<Country> saveCountry(Country country);
	    ResponseEntity<?> deleteCountry(Long id);
	    ResponseEntity<Country> updateCountry(Long id, Country updatedCountry);


}
