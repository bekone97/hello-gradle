package com.example.service;



import com.example.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll(boolean isDeleted);
    public void remove(Long id);
}
