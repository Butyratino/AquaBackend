// SectionsDaoImpl.java
package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.SectionsDto;
import com.sergio.bdas2.backend.model.dto.UserDto;
import com.sergio.bdas2.backend.model.entity.Sections;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SectionsDao {

    private final JdbcTemplate jdbcTemplate;

    public List<SectionsDto> getAllSections() {
        String query = "SELECT * FROM SECTIONS_VIEW ORDER BY SECTIONID";
        return jdbcTemplate.query(query, SectionsDto.getSectionsDtoMapper());
    }

    public SectionsDto getSectionById(Long id) {
        String query = "SELECT * FROM SECTIONS_VIEW WHERE ID_USER = ?";
        List<SectionsDto> foundSections = jdbcTemplate.query(query, new Object[]{id}, SectionsDto.getSectionsDtoMapper());
        if (foundSections.size() != 1) {
            throw new DaoException("Section with ID " + id + " not found or not unique");
        }
        return foundSections.get(0);
    }

    public void updateSection(Integer id, SectionsDto section) {
        String sql = "UPDATE sections " +
                "SET name = ?, " +
                "    description = ?, " +
                "    capacity = ?, " +
                "    addressid = ? " +
                "WHERE sectionid = ?";
        jdbcTemplate.update(sql, section.getName(), section.getDescription(), section.getCapacity(), section.getAddressId(), id);
    }


    public void deleteSection(Integer sectionId) {
        String query = "DELETE FROM sections WHERE sectionid = ?";
        jdbcTemplate.update(query, sectionId);
    }

    public void addSection(SectionsDto section) {
        String query = "INSERT INTO SECTIONS " +
                "(NAME, DESCRIPTION, CAPACITY, ADDRESSID)" +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(
                query,
                section.getName(),
                section.getDescription(),
                section.getCapacity(),
                section.getAddressId()
        );
    }

}


