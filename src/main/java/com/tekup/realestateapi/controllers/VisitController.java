package com.tekup.realestateapi.controllers;


import com.tekup.realestateapi.models.Visit;
import com.tekup.realestateapi.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Visit> saveVisit(@RequestBody Visit visit) {
        return visitService.saveVisit(visit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long id) {
        return visitService.deleteVisit(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody Visit updatedVisit) {
        return visitService.updateVisit(id, updatedVisit);
    }
}
