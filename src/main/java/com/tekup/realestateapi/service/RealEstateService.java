package com.tekup.realestateapi.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.realestateapi.models.EState;
import com.tekup.realestateapi.models.EStates;
import com.tekup.realestateapi.models.RealEstate;

public interface RealEstateService {

    List<RealEstate> getRealEstate();
    ResponseEntity<?> getRealEstateById(Long id);
    ResponseEntity<?> deleteRealEstate(Long id);   
    ResponseEntity<?> updateRealEstate(Long id,List<MultipartFile> files,RealEstate realEstate);
	ResponseEntity<?> addRealEstates(List<MultipartFile> files,RealEstate realEstate);
	
	
	//List<RealEstate> getRealEstateList(Integer pageNumber,Integer pageSize,String field);
    List<RealEstate> getRealEstateList(Integer pageNumber, Integer pageSize, String field, String townName, EStates states,EState state,Integer numFloor);
    List<RealEstate> getRealEstateListByCategory(Integer pageNumber, Integer pageSize,String category);
    List<RealEstate> getAllRealEstateList();

    List<RealEstate> getRealEstateList(Integer pageNumber, Integer pageSize, String field,String townName);
	
	
}