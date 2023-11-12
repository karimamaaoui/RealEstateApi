package com.tekup.realestateapi.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.realestateapi.models.Category;
import com.tekup.realestateapi.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
    private CategoryService catService;
	
    
    /**
     * add new category as 
     */
	 @PostMapping("/add")
		public String uploadImage(@RequestParam("file")MultipartFile file,
				@RequestParam("title") String title
				
				) throws IOException {
			System.out.println(file.getOriginalFilename());
			
            String pathDirectory = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\realestateapi\\src\\main\\resources\\static\\images\\category";
			
			Files.copy(file.getInputStream()
					,Paths.get(pathDirectory+File.separator+file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			
			return catService.createCategoryImg(file, title);

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
	        Category category = catService.getCategory(id);

			catService.deleteCategory(id);
	        deleteImageFile(category.getImage());

	        return ResponseEntity.ok("Category deleted successfully");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}
	}
    private void deleteImageFile(String filename) {
        try {
            String pathDirectory = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\realestateapi\\src\\main\\resources\\static\\images\\category";
            Files.deleteIfExists(Paths.get(pathDirectory + File.separator + filename));
            System.out.println("Image file deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * update category by id
     */
    
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Integer id,
            @RequestPart("title") String title,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            String pathDirectory = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\realestateapi\\src\\main\\resources\\static\\image";

            if (file != null && !file.isEmpty()) {
                Files.copy(
                    file.getInputStream(),
                    Paths.get(pathDirectory + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
                );

                catService.updateCategory(id, title, file);
            } else {
                catService.updateCategory(id, title, null);
            }

            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }


   
}
