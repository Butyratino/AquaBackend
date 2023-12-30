package com.sergio.bdas2.backend.model.entity;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Attractions {
    private int attractionId;
    private String color;
    private int length;
    private int minAge;
    private int sectionId;
    private String name;

    public static RowMapper<Attractions> getAttractionMapper(){
        return (rs, rowNum) -> {
            Attractions attractions = new Attractions();
            attractions.setAttractionId(rs.getInt("ATRAKCEID"));
            attractions.setColor(rs.getString("COLOR"));
            attractions.setLength(rs.getInt("LENGTH"));
            attractions.setMinAge(rs.getInt("MINAGE"));
            attractions.setSectionId(rs.getInt("SECTIONID"));
            attractions.setName(rs.getString("NAME"));

            return attractions;
        };
    }
}
