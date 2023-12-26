package com.tekup.realestateapi.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tekup.realestateapi.models.RealEstate;
import com.tekup.realestateapi.models.User;
import com.tekup.realestateapi.models.Visit;
import com.tekup.realestateapi.repository.RealEstateRepository;
import com.tekup.realestateapi.repository.UserRepository;
import com.tekup.realestateapi.repository.VisitRepository;
import com.tekup.realestateapi.service.VisitService;

@Service
public class VisitServiceImp implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    
    @Autowired
    private UserRepository userRepository; 
    @Autowired
    private RealEstateRepository estateRepository; 
    
    
    @Override
    public ResponseEntity<List<Visit>> getAllVisits() {
        List<Visit> visits = visitRepository.findAll();
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Visit> getVisitById(Long id) {
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isPresent()) {
            return new ResponseEntity<>(visitOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Visit> saveVisit(Visit visit) {
        try {
            Integer userId = visit.getUser().getIdUser();
            User user = userRepository.findById(userId).orElse(null);

            Long realestateId = visit.getRealEstate().getId();
            RealEstate realEstate = estateRepository.findById(realestateId).orElse(null);
            if (user != null && realEstate != null) {
                visit.setUser(user); 
                visit.setRealEstate(realEstate); 
                
                // Save the Visit entity
                Visit savedVisit = visitRepository.save(visit);

                return new ResponseEntity<>(savedVisit, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteVisit(Long id) {
        try {
            visitRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Visit> updateVisit(Long id, Visit updatedVisit) {
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isPresent()) {
            Visit existingVisit = visitOptional.get();
            existingVisit.setDateVisite(updatedVisit.getDateVisite());
            visitRepository.save(existingVisit);
            return new ResponseEntity<>(existingVisit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
