package com.sergio.bdas2.backend.model.entity;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

@Data
public class Employees {
    private int employeeId;
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private String address;
    private int attractionId;
    private int superiorId;

    public static RowMapper<Employees> getEmployeesMapper(){
        return (rs, rowNum) -> {
            Employees employees = new Employees();
            employees.setEmployeeId(rs.getInt("EMPLOYEEID"));
            employees.setName(rs.getString("NAME"));
            employees.setSurname(rs.getString("SURNAME"));
            employees.setDateOfBirth(rs.getTimestamp("DATEOFBIRTH").toLocalDateTime());
            employees.setAttractionId(rs.getInt("ATTRACTIONID"));
            employees.setSuperiorId(rs.getInt("EMPLOYEEID_1"));

            return employees;
        };
    }
}
