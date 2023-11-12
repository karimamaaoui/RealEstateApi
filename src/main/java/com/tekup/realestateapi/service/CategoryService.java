package com.tekup.realestateapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tekup.realestateapi.models.Category;


public interface CategoryService {
	List<Category> getCategory();
	Category getCategory(Integer id);
    void deleteCategory(Integer id);
    void updateCategory(Integer id,String name,MultipartFile file);
    
    void createCategory(Category cat);
    Category createCategoryWithImg(Category cat);
    String createCategoryImg(MultipartFile file, String name);
}
