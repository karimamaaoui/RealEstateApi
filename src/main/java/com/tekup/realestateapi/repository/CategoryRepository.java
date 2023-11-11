package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.realestateapi.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
