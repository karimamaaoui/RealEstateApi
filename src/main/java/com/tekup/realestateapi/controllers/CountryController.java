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
import com.tekup.realestateapi.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
    private CountryService countryService;
	
	 /**
     * add Country
     */

    @PostMapping("/add")
    public ResponseEntity<?> addCountry(@RequestBody Country country) {
    	
        return countryService.addCountry(country);

    }
    
    /**
     * get Countries as list
     */

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getCountries() {
        ResponseEntity<List<Country>> responseEntity = countryService.getCountries();

        if (responseEntity != null && responseEntity.getBody() != null) {
            List<Country> countries = responseEntity.getBody();

            if (countries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(countries, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * get Country by id
     */
    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<?> getCountry(@RequestParam Long id) {
        return countryService.getCountry(id);
    }
   
    
    /**
     * delete Country by id
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        
    	return countryService.deleteCountry(id);

    }

    /**
     * update Country 
     */
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Long id ,@RequestBody Country country){
        
    	return countryService.updateCountry(id,country);

    }
    @GetMapping("/alltowns")
    public ResponseEntity<List<Country>> getCountriesWithTowns() {
    	System.out.println("hell ofrm alltowns");
        return countryService.getCountriesWithTowns();
    }
}
