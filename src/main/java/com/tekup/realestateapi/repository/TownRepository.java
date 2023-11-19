package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.Town;

public interface TownRepository extends JpaRepository<Town, Long>{

}
