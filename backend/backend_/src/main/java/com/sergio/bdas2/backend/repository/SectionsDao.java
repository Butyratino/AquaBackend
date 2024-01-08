package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.SectionsDto;
import com.sergio.bdas2.backend.model.entity.SectionAddressDistrictView;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectionsDao {

    private final JdbcTemplate jdbcTemplate;

    public SectionsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<SectionAddressDistrictView> getAllSections() {
        String query = "SELECT * FROM SectionAddressDistrictView ORDER BY SECTIONID";
        return jdbcTemplate.query(query, SectionAddressDistrictView.getRowMapper());
    }

    public SectionsDto getSectionById(Long id) {
        String query = "SELECT * FROM SECTIONS_VIEW WHERE ID_USER = ?";
        List<SectionsDto> foundSections = jdbcTemplate.query(query, new Object[]{id}, SectionsDto.getSectionsDtoMapper());
        if (foundSections.size() != 1) {
            throw new DaoException("Section with ID " + id + " not found or not unique");
        }
        return foundSections.get(0);
    }

    public void deleteSection(Integer sectionId) {
        String query = "DELETE FROM sections WHERE sectionId = ?";
        jdbcTemplate.update(query, sectionId);
    }

    public void updateSection(Integer id, SectionsDto section) {
        String sql = "{CALL UpdateSection(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, id, section.getName(), section.getDescription(), section.getCapacity(), section.getAddressId());
    }

    public void addSection(SectionsDto section) {
        String query = "{CALL AddSection(?, ?, ?, ?)}";
        jdbcTemplate.update(
                query,
                section.getName(),
                section.getDescription(),
                section.getCapacity(),
                section.getAddressId()
        );
    }

}
