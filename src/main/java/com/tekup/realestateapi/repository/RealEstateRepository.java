package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.RealEstate;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long>{

}
