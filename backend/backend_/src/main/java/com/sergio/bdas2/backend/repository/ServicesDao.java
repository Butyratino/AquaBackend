package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.SectionsDto;
import com.sergio.bdas2.backend.model.dto.ServicesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ServicesDao {
    private final JdbcTemplate jdbcTemplate;

    public List<ServicesDto> getAllServices() {
        String query = "SELECT * FROM ADDITIONALSERVICES ORDER BY ADDITIONALSERVICEID";
        return jdbcTemplate.query(query, ServicesDto.getServicesDtoMapper());
    }

    public ServicesDto getServiceById(Long serviceId) {
        String query = "SELECT * FROM ADDITIONALSERVICES WHERE ADDITIONALSERVICEID = ?";
        List<ServicesDto> foundServices = jdbcTemplate.query(query, new Object[]{serviceId}, ServicesDto.getServicesDtoMapper());
        if (foundServices.size() != 1) {
            throw new DaoException("Service with ID " + serviceId + " not found or not unique");
        }
        return foundServices.get(0);
    }

    public void addService(ServicesDto service) {

        String query = "{CALL AddService(?, ?, ?, ?)}";
        jdbcTemplate.update(
                query,
                service.getSectionId(),
                service.getPrice(),
                service.getDescription(),
                service.getCapacity()
        );
    }

    public void updateService(Integer id, ServicesDto service) {
        String sql = "{CALL UpdateService(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, id, service.getSectionId(), service.getPrice(), service.getDescription(), service.getCapacity());
    }


    public void deleteService(Integer serviceId) {
        String query = "{CALL DeleteService(?)}";
        jdbcTemplate.update(query, serviceId);
    }
}
