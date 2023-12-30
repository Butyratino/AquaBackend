package com.sergio.bdas2.backend.repository;


import com.sergio.bdas2.backend.model.entity.Attractions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Attractions getAttractionById(Integer attractionId){
        String query = "SELECT * FROM ATTRACTIONS WHERE ID = ?";
        List<Attractions> foundAttractions  = jdbcTemplate.query(query, new Object[]{attractionId},
                Attractions.getAttractionMapper());
        if (foundAttractions.size() != 1){
            throw new DaoException("Attraction with ID " + attractionId + " not found");
        }
        return foundAttractions.get(0);
    }
}
