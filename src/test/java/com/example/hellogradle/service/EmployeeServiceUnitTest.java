package com.example.hellogradle.service;


import com.example.model.EmployeeRepository;
import com.example.model.entity.Employee;
import com.example.model.entity.Gender;
import com.example.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceUnitTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl helloService;
    Employee employee;
    @BeforeEach
    public void init(){
        employee=Employee.builder()
                .firstName("Artem")
                .lastName("Myachin")
                .dateOfBirth(LocalDate.now())
                .departmentId(1L)
                .gender(Gender.MALE)
                .employeeId(1L)
                .build();
    }

    @Test
    void getHello() {
        var expected = Collections.singletonList(employee);
        when(employeeRepository.findAll()).thenReturn(expected);
        var actual = helloService.findAll();
        assertEquals(expected,actual);

        verify(employeeRepository).findAll();
    }
}