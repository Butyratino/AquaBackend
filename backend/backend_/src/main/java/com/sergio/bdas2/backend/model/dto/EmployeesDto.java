package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDto {

    private Long employeeId;
    private String name;
    private String surname;
    private Timestamp dateOfBirth;
    private Long attractionId;
    private Long superiorId;
    private Long addressId;
    private Double salary;

    public static RowMapper<EmployeesDto> getEmployeesDtoMapper() {
        return (rs, rowNum) -> {
            EmployeesDto employee = new EmployeesDto();
            employee.setEmployeeId(rs.getLong("EMPLOYEEID"));
            employee.setName(rs.getString("NAME"));
            employee.setSurname(rs.getString("SURNAME"));
            employee.setDateOfBirth(rs.getTimestamp("DATEOFBIRTH"));
            employee.setAttractionId(rs.getLong("ATTRACTIONID"));
            employee.setSuperiorId(rs.getLong("SUPERIORID"));
            employee.setAddressId(rs.getLong("ADDRESSID"));
            employee.setSalary(rs.getDouble("SALARY"));
            return employee;
        };
    }
}
