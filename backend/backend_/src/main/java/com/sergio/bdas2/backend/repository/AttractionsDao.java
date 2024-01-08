package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.AttractionsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AttractionsDao {

    private final JdbcTemplate jdbcTemplate;

    public void addAttraction(AttractionsDto attraction) {
        String query = "{CALL add_attraction(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(
                query,
                attraction.getName(),
                attraction.getColor(),
                attraction.getLength(),
                attraction.getMinAge(),
                attraction.getSectionId()
        );
    }


    public List<AttractionsDto> getAllAttractions() {
        String query = "SELECT * FROM ATTRACTIONS ORDER BY ATTRACTIONID";
        return jdbcTemplate.query(query, AttractionsDto.getAttractionsDtoMapper());
    }



    public AttractionsDto getAttractionById(Integer attractionId) {
        String query = "{CALL get_attraction_by_id(?)}";
        List<AttractionsDto> foundAttractions = jdbcTemplate.query(query, new Object[]{attractionId}, AttractionsDto.getAttractionsDtoMapper());
        if (foundAttractions.size() != 1) {
            throw new DaoException("Attraction with ID " + attractionId + " not found or not unique");
        }
        return foundAttractions.get(0);
    }

    public void updateAttraction(Integer attractionId, AttractionsDto attraction) {
        String sql = "{CALL update_attraction(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, attractionId, attraction.getColor(), attraction.getLength(), attraction.getMinAge(), attraction.getSectionId(), attraction.getName());
    }

    public void deleteAttraction(Integer attractionId) {
        String query = "{CALL DeleteAttraction(?)}";
        jdbcTemplate.update(query, attractionId);
    }

    public Double calculateAvgSalary(Integer attractionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("calculate_average_salary_in_attraction")
                .declareParameters(
                        new SqlParameter("p_attraction_id", Types.INTEGER),
                        new SqlOutParameter("p_average_salary", Types.NUMERIC)
                );

        SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_attraction_id", attractionId);

        Map<String, Object> result = jdbcCall.execute(inParams);

        Number averageSalary = (Number) result.get("p_average_salary");

        System.out.println("Stored Procedure Result: " + result);
        System.out.println("Average Salary: " + averageSalary);

        return averageSalary != null ? averageSalary.doubleValue() : 0.0;
    }






}
