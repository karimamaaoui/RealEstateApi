package com.tekup.realestateapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.realestateapi.models.EState;
import com.tekup.realestateapi.models.EStates;
import com.tekup.realestateapi.models.RealEstate;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    Page<RealEstate> findAll(Pageable pageable);
    Page<RealEstate> findByTownName(String townName, Pageable pageable);
    Page<RealEstate> findByStates(EStates states, Pageable pageable);
    Page<RealEstate> findByTownNameAndStates(String townName, EStates states, Pageable pageable);
    Page<RealEstate> findByTownNameAndStatesAndState(String townName,EStates states, EState state, Pageable pageable);
    Page<RealEstate> findByState(EState state, Pageable pageable);  
    Page<RealEstate>findByTownNameAndState(String townName,EState state,Pageable pageable);

    Page<RealEstate>findByStatesAndState (EStates states,EState state,Pageable pageable);
    Page<RealEstate> findByNumFloor(Integer numFloor, Pageable pageable);

    Page<RealEstate> findByTownNameAndStatesAndStateAndNumFloor(String townName, EStates states, EState state,Integer numFloor, Pageable pageable );
    
    Page<RealEstate> findByTownNameAndStatesAndNumFloor(String townName, EStates states,Integer numFloor,Pageable pageable);
    
    Page<RealEstate> findByTownNameAndStateAndNumFloor(String townName, EState state,Integer numFloor,Pageable pageable);
    
    Page<RealEstate> findByTownNameAndNumFloor(String townName,Integer numFloor,Pageable pageable);
    
    Page<RealEstate> findByStatesAndStateAndNumFloor(EStates states, EState state,Integer numFloor, Pageable pageable);
    
    Page<RealEstate> findByStatesAndNumFloor(EStates states,Integer numFloor,Pageable pageable);
    
    Page<RealEstate> findByStateAndNumFloor(EState state,Integer numFloor, Pageable pageable);
    
    Page<RealEstate> findByCategoryTitle(String categoryTitle, Pageable pageable);

    
    
}
