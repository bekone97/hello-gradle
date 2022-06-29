package com.example.service;



import com.example.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    void remove(Long id);
    Employee getById(Long employeeId);
    Employee save(Employee employee);
    Employee update(Employee employee, long employeeId);
}
