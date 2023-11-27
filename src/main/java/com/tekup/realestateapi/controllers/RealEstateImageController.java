package com.tekup.realestateapi.controllers;

import com.tekup.realestateapi.models.RealEstateImage;
import com.tekup.realestateapi.service.RealEstateImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realEstateImages")
public class RealEstateImageController {

    @Autowired
    private RealEstateImageService realEstateImageService;

    @PostMapping
    public ResponseEntity<RealEstateImage> addRealEstateImage(@RequestBody RealEstateImage realEstateImage) {
        ResponseEntity<?> response = realEstateImageService.addRealEstateImage(realEstateImage);
        return ResponseEntity.status(response.getStatusCode()).body(realEstateImage);
    }

    @GetMapping("/{realEstateId}")
    public ResponseEntity<List<RealEstateImage>> getRealEstateImages(@PathVariable Long realEstateId) {
        List<RealEstateImage> realEstateImages = realEstateImageService.getRealEstateImages(realEstateId);
        return new ResponseEntity<>(realEstateImages, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRealEstateImage(@PathVariable Long id) {
        realEstateImageService.deleteRealEstateImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
