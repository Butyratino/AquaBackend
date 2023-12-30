package com.sergio.bdas2.backend.repository;


import com.sergio.bdas2.backend.model.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employees getEmployeeById(Integer employeeId){
        String query = "SELECT * FROM EMPLOYEES WHERE ID = ?";
        List<Employees> foundEmployees  = jdbcTemplate.query(query, new Object[]{employeeId},
                Employees.getEmployeesMapper());
        if (foundEmployees.size() != 1){
            throw new DaoException("Card with ID " + employeeId + " not found");
        }
        return foundEmployees.get(0);
    }
}
