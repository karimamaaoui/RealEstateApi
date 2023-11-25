package com.tekup.realestateapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tekup.realestateapi.models.TownWithCountryDTO;

import jakarta.persistence.Tuple;

import com.tekup.realestateapi.models.Town;

public interface TownRepository extends JpaRepository<Town, Long>{
    //List<Town> findAllWithCountries();

}
