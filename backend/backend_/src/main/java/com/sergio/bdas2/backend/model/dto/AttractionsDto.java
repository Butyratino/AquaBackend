package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionsDto {
    private Integer attractionId;
    private String color;
    private Integer length;
    private Integer minAge;
    private Integer sectionId;
    private String name;

    public static RowMapper<AttractionsDto> getAttractionsDtoMapper() {
        return (rs, rowNum) -> {
            AttractionsDto attraction = new AttractionsDto();
            attraction.setAttractionId(rs.getInt("ATTRACTIONID"));
            attraction.setColor(rs.getString("COLOR"));
            attraction.setLength(rs.getInt("LENGTH"));
            attraction.setMinAge(rs.getInt("MINAGE"));
            attraction.setSectionId(rs.getInt("SECTIONID"));
            attraction.setName(rs.getString("NAME"));
            return attraction;
        };
    }
}
