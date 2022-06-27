package com.example.service;


import com.example.model.EmployeeRepository;
import com.example.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    public Employee findById(Long employeeId){

        return employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("No employee"));

    }
    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }
}
