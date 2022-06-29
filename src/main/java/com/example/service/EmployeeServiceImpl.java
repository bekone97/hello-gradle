package com.example.service;


import com.example.model.EmployeeRepository;
import com.example.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    public Employee getById(Long employeeId){

        return employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("No employee"));

    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
       return employeeRepository.save(employee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Employee update(Employee employee, long employeeId) {

        return employeeRepository.save(mapUpdatedEmployee(employee, getById(employeeId)));
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
        employee.setVersion(updatedEmployee.getVersion());
        return employee;
    }
}
