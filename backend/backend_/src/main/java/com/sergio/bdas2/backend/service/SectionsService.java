// SectionsServiceImpl.java
package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.SectionsDto;
import com.sergio.bdas2.backend.repository.SectionsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionsService {

    private final SectionsDao sectionsDao;
    private static final Logger logger = LoggerFactory.getLogger(SectionsService.class);


    public List<SectionsDto> getAllSections() {
        return sectionsDao.getAllSections();
    }


    public SectionsDto getSectionById(Long sectionId) {
        return sectionsDao.getSectionById(sectionId);
    }

    public void updateSection(Integer id, SectionsDto section) {
        sectionsDao.updateSection(id, section);
    }


    public void addSection(SectionsDto section) {
        logger.info("Adding new section: {}", section);
        sectionsDao.addSection(section);
    }


    public void deleteSection(Integer sectionId) {
        sectionsDao.deleteSection(sectionId);
    }

    // Add other methods as needed
}
