package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.EmployeesDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeesDao {

    private final JdbcTemplate jdbcTemplate;

    public EmployeesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmployeesDto> getAllEmployees() {
        String query = "SELECT * FROM EMPLOYEES";
        return jdbcTemplate.query(query, EmployeesDto.getEmployeesDtoMapper());
    }

    public EmployeesDto getEmployeeById(Long id) {
        String query = "SELECT * FROM EMPLOYEES WHERE EMPLOYEEID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, EmployeesDto.getEmployeesDtoMapper());
    }

    public void updateEmployee(Long id, EmployeesDto employee) {
        String sql = "UPDATE EMPLOYEES SET NAME = ?, SURNAME = ?, DATEOFBIRTH = ?, ATTRACTIONID = ?, " +
                "SUPERIORID = ?, ADDRESSID = ?, SALARY = ? WHERE EMPLOYEEID = ?";
        jdbcTemplate.update(
                sql,
                employee.getName(),
                employee.getSurname(),
                employee.getDateOfBirth(),
                employee.getAttractionId(),
                employee.getSuperiorId(),
                employee.getAddressId(),
                employee.getSalary(),
                id
        );
    }

    public void addEmployee(EmployeesDto employee) {
        String query = "INSERT INTO EMPLOYEES (NAME, SURNAME, DATEOFBIRTH, ATTRACTIONID, SUPERIORID, ADDRESSID, SALARY) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                employee.getName(),
                employee.getSurname(),
                employee.getDateOfBirth(),
                employee.getAttractionId(),
                employee.getSuperiorId(),
                employee.getAddressId(),
                employee.getSalary()
        );
    }

    public void deleteEmployee(Long employeeId) {
        String query = "DELETE FROM EMPLOYEES WHERE EMPLOYEEID = ?";
        jdbcTemplate.update(query, employeeId);
    }
}
