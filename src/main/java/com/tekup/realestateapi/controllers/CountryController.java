package com.tekup.realestateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.Country;
import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
    private CountryService countryService;
	
	  @GetMapping("/list")
	    public ResponseEntity<List<Country>> getAllCountries() {
	        return countryService.getAllCountries();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
	        return countryService.getCountryById(id);
	    }

	    @PostMapping("/add")
	    public ResponseEntity<Country> saveCountry(@RequestBody Country country) {
	        return countryService.saveCountry(country);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
	        return countryService.deleteCountry(id);
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Country> updateCountry(
	            @PathVariable Long id,
	            @RequestBody Country updatedCountry
	    ) {
	        return countryService.updateCountry(id, updatedCountry);
	    }
}
