package com.tekup.realestateapi.serviceImp;


import com.tekup.realestateapi.models.RealEstate;
import com.tekup.realestateapi.models.RealEstateImage;
import com.tekup.realestateapi.repository.RealEstateImageRepository;
import com.tekup.realestateapi.service.RealEstateImageService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEstateImageServiceImpl implements RealEstateImageService {

    @Autowired
    private RealEstateImageRepository realEstateImageRepository;

    @Override
    public ResponseEntity<?> addRealEstateImage(RealEstateImage realEstateImage) {
        realEstateImageRepository.save(realEstateImage);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<RealEstateImage> getRealEstateImages(Long realEstateId) {
        return realEstateImageRepository.findByRealEstateId(realEstateId);
    }

    @Override
    public ResponseEntity<?> deleteRealEstateImage(Long id) {
        realEstateImageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @Transactional
    public void deleteImagesByRealEstate(RealEstate realEstate) {
    	realEstateImageRepository.deleteByRealEstate(realEstate);
    }
}
