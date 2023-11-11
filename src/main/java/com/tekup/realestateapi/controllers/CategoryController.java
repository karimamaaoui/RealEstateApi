package com.tekup.realestateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.realestateapi.models.Category;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
    private CategoryService catService;
	
	/**
     * add category
     */

    @PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
    public String addCategory(@RequestBody Category cat) {
    	
        catService.createCategory(cat);

        return "success add cateogry";
    }
    
    /**
     * get categories as list
     */

    @GetMapping("/list")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public List<Category> getUsers() {
        return catService.getCategory();
    }

    /**
     * get category by id
     */

    @GetMapping("/get")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public Category getCategory(@RequestParam Integer id) {
        return catService.getCategory(id);
    }
    
    /**
     * delete category by id
     */
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
		try {
			catService.deleteCategory(id);
	        return ResponseEntity.ok("Category deleted successfully");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}
	}
    /**
     * update category by id
     */
    
    @PutMapping("update/{id}")
    public ResponseEntity<String> UpdateCategory(@PathVariable Integer id, @RequestBody Category category) {
		try {
			catService.updateCategory(id,category);
	        return ResponseEntity.ok("Category updated successfully");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}
	}

}
