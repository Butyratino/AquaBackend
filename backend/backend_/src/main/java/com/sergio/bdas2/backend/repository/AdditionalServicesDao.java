package com.sergio.bdas2.backend.repository;


import com.sergio.bdas2.backend.model.entity.AdditionalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AdditionalServicesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdditionalServices getAdditionalServiceById(Integer additionalServiceId){
        String query = "SELECT * FROM ADDITIONALSERVICES WHERE ID = ?";
        List<AdditionalServices> foundAdditionalServices  = jdbcTemplate.query(query, new Object[]{additionalServiceId},
                AdditionalServices.getAdditionalServicesMapper());
        if (foundAdditionalServices.size() != 1){
            throw new DaoException("Additional Service with ID " + additionalServiceId + " not found");
        }
        return foundAdditionalServices.get(0);
    }
}
