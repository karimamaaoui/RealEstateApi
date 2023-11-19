package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.RealEstate;

public interface RealEstateService {

	ResponseEntity<?> addRealEstate(RealEstate real);
    List<RealEstate> getRealEstate();
    ResponseEntity<?>  getRealEstate(Long id);
    ResponseEntity<?> deleteRealEstate(Long id);   
    ResponseEntity<?> updateRealEstate(Long id, RealEstate real);

}