package com.tekup.realestateapi.service;

import java.util.List;

import com.tekup.realestateapi.models.Category;


public interface CategoryService {
	List<Category> getCategory();
	Category getCategory(Integer id);
    void deleteCategory(Integer id);
    void updateCategory(Integer id,Category cat);
    
    void createCategory(Category cat);
}
