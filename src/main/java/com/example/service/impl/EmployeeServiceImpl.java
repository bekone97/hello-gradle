package com.example.service.impl;


import com.example.model.EmployeeRepository;
import com.example.model.entity.Employee;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    @Cacheable(value = "employee",key = "#employeeId")
    public Employee getById(Long employeeId){
        log.error("Get employee by {}", employeeId);
        return employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("No employee"));

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Employee save(Employee employee) {
        log.info("Save Employee = {}",employee);
       return employeeRepository.save(employee);

    }


    @Override
    @Transactional
    @CachePut(value = "employee",key = "#employeeId")
    public Employee update(Employee employee, long employeeId) {
        log.info("Update Employee = {} and employeeId ={}",employee,employeeId);

        var updateEmp= employeeRepository.save(mapUpdatedEmployee(employee, getById(employeeId)));
        return updateEmp;
    }



    @Override
    @Transactional
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }


    private Employee mapUpdatedEmployee(Employee employee, Employee updatedEmployee) {
        employee.setEmployeeId(updatedEmployee.getEmployeeId());
        employee.setCreatedAt(updatedEmployee.getCreatedAt());
        employee.setCreatedBy(updatedEmployee.getCreatedBy());
//        employee.setVersion(updatedEmployee.getVersion());
        return employee;
    }
}
