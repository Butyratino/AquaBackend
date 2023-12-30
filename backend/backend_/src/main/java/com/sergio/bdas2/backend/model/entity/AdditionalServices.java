package com.sergio.bdas2.backend.model.entity;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class AdditionalServices {
    private int additionalServiceId;
    private int sectionID;
    private int userId;
    private float price;
    private String description;
    private int capacity;

    public static RowMapper<AdditionalServices> getAdditionalServicesMapper(){
        return (rs, rowNum) -> {
            AdditionalServices additionalServices = new AdditionalServices();
            additionalServices.setAdditionalServiceId(rs.getInt("ADDITIONALSERVICEID"));
            additionalServices.setSectionID(rs.getInt("SECTIONID"));
            additionalServices.setUserId(rs.getInt("USERID"));
            additionalServices.setPrice(rs.getFloat("PRICE"));
            additionalServices.setDescription(rs.getString("DESCRIPTION"));
            additionalServices.setCapacity(rs.getInt("CAPACITY"));

            return additionalServices;
        };
    }

}
