package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.AttractionsDto;
import com.sergio.bdas2.backend.service.AttractionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
public class AttractionsController {

    private final AttractionsService attractionsService;

    public AttractionsController(AttractionsService attractionsService) {
        this.attractionsService = attractionsService;
    }

    @GetMapping("/all")
    public List<AttractionsDto> getAllAttractions() {
        List<AttractionsDto> attractions = attractionsService.getAllAttractions();
        return attractions;
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<AttractionsDto> getAttractionById(@PathVariable Integer attractionId) {
        AttractionsDto attraction = attractionsService.getAttractionById(attractionId);
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAttraction(@RequestBody AttractionsDto attraction) {
        System.out.println("Received POST request to add an attraction");
        attractionsService.addAttraction(attraction);
        return new ResponseEntity<>("Attraction added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{attractionId}")
    public ResponseEntity<Void> updateAttraction(@PathVariable Integer attractionId, @RequestBody AttractionsDto attraction) {
        attraction.setAttractionId(attractionId);
        attractionsService.updateAttraction(attractionId, attraction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{attractionId}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Integer attractionId) {
        attractionsService.deleteAttraction(attractionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/avg1/{attractionId}")
    public Double calculateAvgSalary(@PathVariable Integer attractionId) {
        System.out.println("Received GET request to calc an avg salary");
        return attractionsService.calculateAvgSalary(attractionId);
    }

}
