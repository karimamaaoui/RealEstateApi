package com.tekup.realestateapi.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
	public void updateCategory(Integer id,Category cat) {
		categoryRepository.findById(id)
	    		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid cateogry Id:" + id));
		
		cat.setIdCat(id);
	    
	    categoryRepository.save(cat);
	}
	

}
