package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.EmployeesDto;
import com.sergio.bdas2.backend.repository.EmployeesDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesService {

    private final EmployeesDao employeesDao;

    public EmployeesService(EmployeesDao employeesDao) {
        this.employeesDao = employeesDao;
    }

    public List<EmployeesDto> getAllEmployees() {
        return employeesDao.getAllEmployees();
    }

    public EmployeesDto getEmployeeById(Long employeeId) {
        return employeesDao.getEmployeeById(employeeId);
    }

    public void updateEmployee(Long id, EmployeesDto employee) {
        employeesDao.updateEmployee(id, employee);
    }

    public void addEmployee(EmployeesDto employee) {
        employeesDao.addEmployee(employee);
    }

    public void deleteEmployee(Long employeeId) {
        employeesDao.deleteEmployee(employeeId);
    }
}
