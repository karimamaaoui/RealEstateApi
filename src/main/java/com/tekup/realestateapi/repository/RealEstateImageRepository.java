package com.tekup.realestateapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.RealEstate;
import com.tekup.realestateapi.models.RealEstateImage;


public interface RealEstateImageRepository extends JpaRepository<RealEstateImage, Long > {
	List<RealEstateImage> findByRealEstateId(Long realEstateId);
    void deleteByRealEstate(RealEstate realEstate);
    RealEstateImage findByImageNameAndRealEstate(String imageName, RealEstate realEstate);

}
