package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Town;

public interface TownService {
	ResponseEntity<?> addTown(Town town);
    List<Town> getTowns();
    ResponseEntity<?>  getTown(Long id);
    ResponseEntity<?> deleteTown(Long id);   
    ResponseEntity<?> updateTown(Long id, Town town);

}
