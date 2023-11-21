package com.tekup.realestateapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.models.TownWithCountryDTO;
import com.tekup.realestateapi.service.TownService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/town")
public class TownController {
	
	@Autowired
    private TownService townService;
	
	
	 /**
     * add town
     */
/*
	@PostMapping("/add")
	public ResponseEntity<?> addTown(@RequestBody Town town) {
	    try {
	        return townService.addTown(town);
	    } catch (Exception e) {
	        // Handle any exceptions, e.g., constraint violations
	        return new ResponseEntity<>("Error creating Town: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}*/
	 @PostMapping("/add")
	    public ResponseEntity<?> addTown(@RequestBody Town town) {
	        try {
	        	System.out.println("town body "+town);
	            return townService.addTown(town);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating town: " + e.getMessage());
	        }
	    }

	

	@GetMapping("/list")
    public ResponseEntity<?> getAllTownsWithCountries() {
        try {
            List<TownWithCountryDTO> towns = townService.getTownsWithCountries();

            if (towns.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No towns found");
            } else {
                return ResponseEntity.ok(towns);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving towns: " + e.getMessage());
        }
    } 
    /**
     * get Town by id
     */
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTownById(@PathVariable Long id) {
	    try {
	        Optional<TownWithCountryDTO> optionalTown = townService.getTownWithCountryById(id);
	        
	        if (optionalTown.isPresent()) {
	            TownWithCountryDTO townDTO = optionalTown.get();
	            return ResponseEntity.ok(townDTO);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Town not found with ID: " + id);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving town: " + e.getMessage());
	    }
	}

    /**
     * delete Town by id
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTown(@PathVariable Long id){
        
    	return townService.deleteTown(id);

    }

    /**
     * update Town 
     */
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTown(@PathVariable Long id ,@RequestBody Town town){
        
    	return townService.updateTown(id,town);

    }
}