package com.example.service;



import com.example.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    void remove(Long id);
    Employee findById(Long employeeId);
}
