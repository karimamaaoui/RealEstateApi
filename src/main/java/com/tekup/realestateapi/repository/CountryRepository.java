package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long > {

}
