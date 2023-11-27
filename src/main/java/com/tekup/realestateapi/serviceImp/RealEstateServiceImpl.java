package com.tekup.realestateapi.serviceImp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.realestateapi.models.Category;
import com.tekup.realestateapi.models.RealEstate;
import com.tekup.realestateapi.models.RealEstateImage;
import com.tekup.realestateapi.models.Town;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.repository.CategoryRepository;
import com.tekup.realestateapi.repository.RealEstateImageRepository;
import com.tekup.realestateapi.repository.RealEstateRepository;
import com.tekup.realestateapi.repository.TownRepository;
import com.tekup.realestateapi.repository.UserRepository;
import com.tekup.realestateapi.service.RealEstateService;

@Service
public class RealEstateServiceImpl implements RealEstateService {
	
	@Autowired
	private RealEstateRepository realEstateRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TownRepository townRepository;

	@Autowired
	private RealEstateImageRepository estateImageRepository;

	@Override
	public List<RealEstate> getRealEstate() {
		return realEstateRepository.findAll();
	}
	@Override
	public ResponseEntity<?> getRealEstateById(Long id) {
	    RealEstate realEstate = realEstateRepository.findById(id).orElse(null);
	    if (realEstate != null) {
	        return ResponseEntity.ok(realEstate);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	/*@Override
	public ResponseEntity<?> addRealEstates(RealEstate realEstate) {
	    // Fetch category from the database based on the provided category ID
	    Integer catId = realEstate.getCategory().getIdCat();
	    Category cat = categoryRepository.findById(catId).orElse(null);
	    Integer userId = realEstate.getUser().getIdUser();
	    User user = userRepository.findById(userId).orElse(null);

	    if (cat != null) {
	        // Set the category in the real estate
	        realEstate.setCategory(cat);

	        if (user != null) {
	            // Set the category in the real estate
	            realEstate.setUser(user);

	            // Fetch town from the database based on the provided town ID
	            Long townId = realEstate.getTown().getIdTown();
	            Town town = townRepository.findById(townId).orElse(null);

	            if (town != null) {
	                // Set the town in the real estate
	                realEstate.setTown(town);

	                // Save the real estate to associate it with the category and town
	                RealEstate savedRealEstate = realEstateRepository.save(realEstate);
	                List<RealEstateImage> images = realEstate.getImages();
	                System.out.println("images "+images);
	                
	                if (images != null) {
	                    for (RealEstateImage image : images) {
	                        // Check and set the image name
	                        String imageName = image.getImageName();

	                        if (imageName == null) {
		    	                System.out.println("imageName "+imageName);

	                        	// Handle the case where image name is null
	                            // You might want to set a default name or throw an exception
	                        }
	                        
	                        image.setImageName(imageName);
	                        image.setRealEstate(savedRealEstate);
	                        estateImageRepository.save(image);
	                    }
	                }
	                return new ResponseEntity<>(savedRealEstate, HttpStatus.OK);
	            } else {
	                // Handle the case where the town with the provided ID is not found
	                return new ResponseEntity<>("Town not found with ID: " + townId, HttpStatus.NOT_FOUND);
	            }
	        } else {
	            // Handle the case where the user with the provided ID is not found
	            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
	        }
	    } else {
	        // Handle the case where the category with the provided ID is not found
	        return new ResponseEntity<>("Category not found with ID: " + catId, HttpStatus.NOT_FOUND);
	    }
	}
	@Override
	public ResponseEntity<?> addRealEstates(RealEstate realEstate) {
	    Integer catId = realEstate.getCategory().getIdCat();
	    Category cat = categoryRepository.findById(catId).orElse(null);
	    Integer userId = realEstate.getUser().getIdUser();
	    User user = userRepository.findById(userId).orElse(null);

	    if (cat != null) {
	        realEstate.setCategory(cat);

	        if (user != null) {
	            realEstate.setUser(user);

	            Long townId = realEstate.getTown().getIdTown();
	            Town town = townRepository.findById(townId).orElse(null);

	            if (town != null) {
	                realEstate.setTown(town);

	                RealEstate savedRealEstate = realEstateRepository.save(realEstate);
	                List<RealEstateImage> images = realEstate.getImages();

	                if (images != null) {
	                    for (RealEstateImage image : images) {
	                        // Set the reference to the saved RealEstate in each RealEstateImage
	                        image.setRealEstate(savedRealEstate);
	                        estateImageRepository.save(image);
	                    }
	                }

	                return new ResponseEntity<>("real estate saved successfully", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Town not found with ID: " + townId, HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
	        }
	    } else {
	        return new ResponseEntity<>("Category not found with ID: " + catId, HttpStatus.NOT_FOUND);
	    }
	}
*/
/*
@Override
	public ResponseEntity<?> addRealEstates(@RequestPart("file") MultipartFile file,
	                                        @RequestPart RealEstate realEstate) {
	    Integer catId = realEstate.getCategory().getIdCat();
	    Category cat = categoryRepository.findById(catId).orElse(null);
	    Integer userId = realEstate.getUser().getIdUser();
	    User user = userRepository.findById(userId).orElse(null);

	    if (cat != null) {
	        realEstate.setCategory(cat);

	        if (user != null) {
	            realEstate.setUser(user);

	            Long townId = realEstate.getTown().getIdTown();
	            Town town = townRepository.findById(townId).orElse(null);

	            if (town != null) {
	                realEstate.setTown(town);

	                RealEstate savedRealEstate = realEstateRepository.save(realEstate);

	                // Save the image directly using the provided MultipartFile
	                String pathDirectory = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\realestateapi\\src\\main\\resources\\static\\images\\realestate";
	                String imageName = file.getOriginalFilename();

	                try {
	                    Files.copy(
	                            file.getInputStream(),
	                            Paths.get(pathDirectory + File.separator + imageName),
	                            StandardCopyOption.REPLACE_EXISTING
	                    );
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    return new ResponseEntity<>("Failed to save image", HttpStatus.INTERNAL_SERVER_ERROR);
	                }

	                // Update the image name with the saved file name
	                RealEstateImage image = new RealEstateImage();
	                image.setImageName(imageName);
	                image.setRealEstate(savedRealEstate);
	                estateImageRepository.save(image);

	                return new ResponseEntity<>("Real estate saved successfully", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Town not found with ID: " + townId, HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
	        }
	    } else {
	        return new ResponseEntity<>("Category not found with ID: " + catId, HttpStatus.NOT_FOUND);
	    }
	}


*/

	@Override
	public ResponseEntity<?> addRealEstates(@RequestPart("files") List<MultipartFile> files,
	                                        @RequestPart RealEstate realEstate) {
	    Integer catId = realEstate.getCategory().getIdCat();
	    Category cat = categoryRepository.findById(catId).orElse(null);
	    Integer userId = realEstate.getUser().getIdUser();
	    User user = userRepository.findById(userId).orElse(null);

	    if (cat != null) {
	        realEstate.setCategory(cat);

	        if (user != null) {
	            realEstate.setUser(user);

	            Long townId = realEstate.getTown().getIdTown();
	            Town town = townRepository.findById(townId).orElse(null);

	            if (town != null) {
	                realEstate.setTown(town);

	                RealEstate savedRealEstate = realEstateRepository.save(realEstate);

	                // Save the image directly using the provided MultipartFile
	                String pathDirectory = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\realestateapi\\src\\main\\resources\\static\\images\\realestate";
	                for (MultipartFile file : files) {
	                    String imageName = file.getOriginalFilename();

	                    try {
	                        Files.copy(
	                                file.getInputStream(),
	                                Paths.get(pathDirectory + File.separator + imageName),
	                                StandardCopyOption.REPLACE_EXISTING
	                        );
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        return new ResponseEntity<>("Failed to save images", HttpStatus.INTERNAL_SERVER_ERROR);
	                    }

	                    // Update the image name with the saved file name
	                    RealEstateImage image = new RealEstateImage();
	                    image.setImageName(imageName);
	                    image.setRealEstate(savedRealEstate);
	                    estateImageRepository.save(image);
	                }
	                return new ResponseEntity<>("Real estate saved successfully", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Town not found with ID: " + townId, HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
	        }
	    } else {
	        return new ResponseEntity<>("Category not found with ID: " + catId, HttpStatus.NOT_FOUND);
	    }
	}


	
	@Override
	public ResponseEntity<?> deleteRealEstate(Long id) {
		realEstateRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<?> updateRealEstate(Long id, RealEstate updatedRealEstate) {
		if (realEstateRepository.existsById(id)) {
			updatedRealEstate.setId(id);
			realEstateRepository.save(updatedRealEstate);
			return ResponseEntity.ok(updatedRealEstate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
