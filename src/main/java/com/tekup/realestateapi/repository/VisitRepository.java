package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.realestateapi.models.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
