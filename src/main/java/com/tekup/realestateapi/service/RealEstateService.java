package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.realestateapi.models.RealEstate;

public interface RealEstateService {

    List<RealEstate> getRealEstate();
    ResponseEntity<?> getRealEstateById(Long id);
    ResponseEntity<?> deleteRealEstate(Long id);   
    ResponseEntity<?> updateRealEstate(Long id,List<MultipartFile> files,RealEstate realEstate);
	ResponseEntity<?> addRealEstates(List<MultipartFile> files,RealEstate realEstate);

}