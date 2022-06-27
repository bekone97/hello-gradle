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
    public List<Employee> getHello(@RequestParam(required = false,defaultValue = "false") boolean isDeleted){
        if (isDeleted) {
            return employeeService.findAll(true);
        }else {
            return employeeService.findAll(false);
        }
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Long employeeId){
        employeeService.remove(employeeId);
    }
}
