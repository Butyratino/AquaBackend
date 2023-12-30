package com.sergio.bdas2.backend.model.entity;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Sections {

    private int sectionId;
    private String name;
    private String description;
    private int capacity;

    public static RowMapper<Sections> getSectionsMapper(){
        return (rs, rowNum) -> {
            Sections sections = new Sections();
            sections.setSectionId(rs.getInt("SECTIONID"));
            sections.setName(rs.getString("NAME"));
            sections.setDescription(rs.getString("DESCRIPTION"));
            sections.setCapacity(rs.getInt("CAPACITY"));

            return sections;
        };
    }


}
