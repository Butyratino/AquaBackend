package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDto {

    private Integer additionalServiceId; // change this property name
    private Integer sectionId; // change this property name
    private Integer price;
    private String description;
    private Integer capacity;


    public static RowMapper<ServicesDto> getServicesDtoMapper() {
        return (rs, rowNum) -> {
            ServicesDto service = new ServicesDto();
            service.setAdditionalServiceId(rs.getInt("ADDITIONALSERVICEID"));
            service.setSectionId(rs.getInt("SECTIONID"));
            service.setPrice(rs.getInt("PRICE"));
            service.setDescription(rs.getString("DESCRIPTION"));
            service.setCapacity(rs.getInt("CAPACITY"));
            return service;
        };
    }

}
