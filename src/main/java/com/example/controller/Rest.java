package com.example.controller;

import com.example.model.entity.Employee;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class Rest {
    private final EmployeeService employeeService;
    @GetMapping
    public List<Employee> getHello(){
            return employeeService.findAll();

    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Long employeeId){
        employeeService.remove(employeeId);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId){
        return employeeService.findById(employeeId);
    }
}
