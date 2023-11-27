package com.tekup.realestateapi.serviceImp;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.tekup.realestateapi.models.Category;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.repository.CategoryRepository;
import com.tekup.realestateapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public List<Category> getCategory() {
        return categoryRepository.findAll();

	}

	@Override
	public Category getCategory(Integer id) {
	    Category cat = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid cateogry Id:" + id));

        return cat;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category cat = categoryRepository
	                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid cateogry Id:" + id));

	        categoryRepository.delete(cat);

	}

	@Override
	public void createCategory(Category cat) {
		categoryRepository.save(cat);
	}

	@Override
	public void updateCategory(Integer id, String title, MultipartFile file) {
	    Category existingCategory = categoryRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid category Id: " + id));

	    // Update category information
	    existingCategory.setTitle(title);

	    // Update image only if a new file is provided
	    if (file != null && !file.isEmpty()) {
	        try {
	            existingCategory.setImage(file.getOriginalFilename());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    categoryRepository.save(existingCategory);
	}

	@Override
	public Category createCategoryWithImg(Category cat) {
		return categoryRepository.save(cat);

	}

	@Override
	public Category createCategoryImg(MultipartFile file, String name) {
			Category category = new Category();
			category.setTitle(name);
			category.setImage(file.getOriginalFilename());
			System.out.println("image "+category);
			return categoryRepository.save(category);
		
	}
	

}
