package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.EmployeesDto;
import com.sergio.bdas2.backend.service.EmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/all")
    public List<EmployeesDto> getAllEmployees() {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeesDto> getEmployeeById(@PathVariable Long employeeId) {
        EmployeesDto employee = employeesService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeesDto employee) {
        employeesService.addEmployee(employee);
        return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeesDto employee) {
        employee.setEmployeeId(employeeId);
        employeesService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeesService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
