package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.SectionsDto;
import com.sergio.bdas2.backend.model.dto.ServicesDto;
import com.sergio.bdas2.backend.service.ServicesService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addservices")
public class ServicesController {

    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping("/all")
    public List<ServicesDto> getAllServices() {
        List<ServicesDto> services = servicesService.getAllServices();
        return services;
    }


    @GetMapping("/{serviceId}")
    public ResponseEntity<ServicesDto> getServicesById(@PathVariable Long serviceId) {
        ServicesDto services = servicesService.getServiceById(serviceId);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addService(@RequestBody ServicesDto service) {
        System.out.println("Received POST request to add a service!");
        servicesService.addService(service);
        return new ResponseEntity<>("Service added successfully", HttpStatus.CREATED);
    }

    // Add the following method to handle OPTIONS requests
    @RequestMapping(value = "/add", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        System.out.println("Received OPTIONS request for adding a service");
        return ResponseEntity.ok().allow(HttpMethod.POST).build();
    }

    @PutMapping("/update/{serviceId}")
    public ResponseEntity<Void> updateservice(@PathVariable Integer serviceId, @RequestBody ServicesDto service) {
        service.setAdditionalServiceId(serviceId);
        servicesService.updateService(serviceId, service);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer serviceId) {
        servicesService.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
