package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.RealEstateImage;

public interface RealEstateImageService {

	 ResponseEntity<?> addRealEstateImage(RealEstateImage realEstateImage);
	    List<RealEstateImage> getRealEstateImages(Long realEstateId);
	    ResponseEntity<?> deleteRealEstateImage(Long id);
}