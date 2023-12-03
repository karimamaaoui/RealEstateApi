package com.tekup.realestateapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekup.realestateapi.models.Category;
import com.tekup.realestateapi.models.EState;
import com.tekup.realestateapi.models.EStates;
import com.tekup.realestateapi.models.RealEstate;
import com.tekup.realestateapi.service.CategoryService;
import com.tekup.realestateapi.service.RealEstateService;

import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/realestate")
public class RealEstateController {

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResponseEntity<List<RealEstate>> getAllRealEstates() {
        List<RealEstate> realEstates = realEstateService.getRealEstate();
        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRealEstateById(@PathVariable Long id) {
        ResponseEntity<?> response = realEstateService.getRealEstateById(id);
        if (response.getBody() != null) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }
/*
    @PostMapping("/add")
    public ResponseEntity<?> saveRealEstate(
            @RequestParam("imageFiles") List<MultipartFile> imageFiles,

    		@RequestBody RealEstate realEstate
    		) {

        ResponseEntity<?> response = realEstateService.addRealEstate(realEstate);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }*/
  
   
    @PostMapping("/add")
    public ResponseEntity<?> createRealEstate(@RequestPart("files") List<MultipartFile> files,
    		@RequestPart RealEstate estate) {
    	System.out.println("file "+files.toString());
        return realEstateService.addRealEstates(files,estate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRealEstate(@PathVariable Long id,@RequestPart(value = "files", required = false) List<MultipartFile> files,
    		@RequestPart RealEstate estate) {
    	//System.out.println("file "+files.toString());
        return realEstateService.updateRealEstate(id,files,estate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRealEstate(@PathVariable Long id) {
        realEstateService.deleteRealEstate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    
  /*  @GetMapping("/getAllRealEstates")
    public ResponseEntity<List<RealEstate>> getAllPost(
    		@RequestParam(value="pageNumber",defaultValue = "0",required = false)Integer pageNumber,
    		@RequestParam(value="pageSize",defaultValue = "5",required = false)Integer pageSize,
    		@RequestParam(value="field",defaultValue = "",required = false)String field

    		) {
    	System.out.println("hello");
        List<RealEstate> realEstates = realEstateService.getRealEstateList(pageNumber,pageSize,field);
        
        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }
    
    @GetMapping("/getAllRealEstates")
    public ResponseEntity<List<RealEstate>> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "field", defaultValue = "", required = false) String field,
            @RequestParam(value = "townName", defaultValue = "", required = false) String townName
            
    ) {
        List<RealEstate> realEstates = realEstateService.getRealEstateList(pageNumber, pageSize, field, townName);

        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }
    */
    @GetMapping("/getAllRealEstates")
    public ResponseEntity<List<RealEstate>> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "field", defaultValue = "", required = false) String field,
            @RequestParam(value = "townName", defaultValue = "", required = false) String townName,
            @RequestParam(value = "states", defaultValue = "", required = false) EStates states,
            @RequestParam(value = "state", defaultValue = "", required = false) EState state,
            @RequestParam(value = "numFloor", defaultValue = "0", required = false) Integer numFloor
            
    ) {
        List<RealEstate> realEstates = realEstateService.getRealEstateList(pageNumber, pageSize, field, townName,states,state,numFloor);

        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }
    
    
}
