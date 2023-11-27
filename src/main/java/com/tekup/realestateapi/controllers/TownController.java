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
	
	
	
	 @GetMapping("/list")
	    public ResponseEntity<List<Town>> getAllTowns() {
	        return townService.getAllTowns();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Town> getTownById(@PathVariable Long id) {
	        return townService.getTownById(id);
	    }
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Town> updateTown(
	            @PathVariable Long id,
	            @RequestBody Town updatedTown
	    ) {
	        return townService.updateTown(id, updatedTown);
	    }
	    @PostMapping("/add")
	    public ResponseEntity<Town> saveTown(@RequestBody Town town) {
	        return townService.saveTown(town);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteTown(@PathVariable Long id) {
	        return townService.deleteTown(id);
	    }
	    @PutMapping("/update/{idtown}/country/{idcountry}")
	    public ResponseEntity<Town> addCountryToTown(
	    	@PathVariable Long idtown,
	    	@PathVariable Long idcountry
	    )
	    {
	    return townService.addCountryToTown(idtown,idcountry);	
	    }
	    
	    @PutMapping("/updateCountry/{idtown}/country/{idcountry}")
	    public ResponseEntity<Town> updateCountryInTown(
	            @PathVariable Long idtown,
	            @PathVariable Long idcountry
	    ) {
	        return townService.updateCountryInTown(idtown, idcountry);
	    }
	    
}