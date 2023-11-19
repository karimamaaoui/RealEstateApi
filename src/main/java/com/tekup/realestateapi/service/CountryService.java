package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Country;


public interface CountryService {

	ResponseEntity<?> addCountry(Country country);
    List<Country> getCountries();
    ResponseEntity<?>  getCountry(Long id);
    ResponseEntity<?> deleteCountry(Long id);   
    ResponseEntity<?> updateCountry(Long id, Country country);

}
