package com.example.service.impl;


import com.example.model.EmployeeRepository;
import com.example.model.entity.Employee;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher applicationEventPublisher;
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    public Employee getById(Long employeeId){

        return employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("No employee"));

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Employee save(Employee employee) {
        log.info("Save Employee = {}",employee);
       applicationEventPublisher.publishEvent(employee);
       return employeeRepository.save(employee);

    }


    @Override
    @Transactional
    public Employee update(Employee employee, long employeeId) {
        log.info("Update Employee = {}",employee);

        var updateEmp= employeeRepository.save(mapUpdatedEmployee(employee, getById(employeeId)));
        applicationEventPublisher.publishEvent(updateEmp);
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
