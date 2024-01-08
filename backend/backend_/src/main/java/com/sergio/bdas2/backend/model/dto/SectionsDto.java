package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionsDto {
    private Integer sectionId;
    private String name;
    private String description;
    private Integer capacity;
    private Long addressId;

    public static RowMapper<SectionsDto> getSectionsDtoMapper() {
        return (rs, rowNum) -> {
            SectionsDto section = new SectionsDto();
            section.setSectionId(rs.getInt("SECTIONID"));
            section.setName(rs.getString("NAME"));
            section.setDescription(rs.getString("DESCRIPTION"));
            section.setCapacity(rs.getInt("CAPACITY"));
            section.setAddressId(rs.getLong("ADDRESSID"));
            return section;
        };
    }
}
