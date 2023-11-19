package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tekup.realestateapi.models.Images;

public interface ImagesService {

	ResponseEntity<?> addImages(Images img);
    List<Images> getImages();
    ResponseEntity<?>  getImages(Long id);
    ResponseEntity<?> deleteImages(Long id);   
    ResponseEntity<?> updateImages(Long id, Images img);

}