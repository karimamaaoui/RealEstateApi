package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Visit;


public interface VisitService {

	
  	ResponseEntity<List<Visit>> getAllVisits();
    ResponseEntity<Visit> getVisitById(Long id);
    ResponseEntity<Visit> saveVisit(Visit visit);
    ResponseEntity<?> deleteVisit(Long id);
    ResponseEntity<Visit> updateVisit(Long id, Visit updatedVisit);

}
