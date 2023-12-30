// SectionsController.java
package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.SectionsDto;
import com.sergio.bdas2.backend.service.SectionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpMethod;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionsController {

    private final SectionsService sectionsService;

    public SectionsController(SectionsService sectionsService) {
        this.sectionsService = sectionsService;
    }

    @GetMapping("/all")
    public List<SectionsDto> getAllSections() {
        List<SectionsDto> sections = sectionsService.getAllSections();
        return sections;
    }


//    @PutMapping(value = "/update/{id}")
//    public void updateSection(@PathVariable(value = "id") Integer id, @RequestBody SectionsDto section) {
//        sectionsService.updateSection(id, section);
//    }


    @GetMapping("/{sectionId}")
    public ResponseEntity<SectionsDto> getSectionById(@PathVariable Long sectionId) {
        SectionsDto section = sectionsService.getSectionById(sectionId);
        return new ResponseEntity<>(section, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addSection(@RequestBody SectionsDto section) {
        System.out.println("Received POST request to add a section");
        sectionsService.addSection(section);
        return new ResponseEntity<>("Section added successfully", HttpStatus.CREATED);
    }

    // Add the following method to handle OPTIONS requests
    @RequestMapping(value = "/add", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        System.out.println("Received OPTIONS request for adding a section");
        return ResponseEntity.ok().allow(HttpMethod.POST).build();
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<Void> updateSection(@PathVariable Integer id, @RequestBody SectionsDto section) {
        section.setSectionId(id);
        sectionsService.updateSection(id, section);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sectionId}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer sectionId) {
        sectionsService.deleteSection(sectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    // Add other methods as needed
}
