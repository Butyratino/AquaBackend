package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.AttractionsDto;
import com.sergio.bdas2.backend.repository.AttractionsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionsService {

    private final AttractionsDao attractionsDao;
    private static final Logger logger = LoggerFactory.getLogger(AttractionsService.class);

    public List<AttractionsDto> getAllAttractions() {
        return attractionsDao.getAllAttractions();
    }

    public AttractionsDto getAttractionById(Integer attractionId) {
        return attractionsDao.getAttractionById(attractionId);
    }

    public void updateAttraction(Integer id, AttractionsDto attraction) {
        attractionsDao.updateAttraction(id, attraction);
    }

    public void addAttraction(AttractionsDto attraction) {
        logger.info("Adding new attraction: {}", attraction);
        attractionsDao.addAttraction(attraction);
    }

    public void deleteAttraction(Integer attractionId) {
        attractionsDao.deleteAttraction(attractionId);
    }

    public Double calculateAvgSalary(Integer attractionId) {
        return attractionsDao.calculateAvgSalary(attractionId);
    }

    // Add other methods as needed
}
