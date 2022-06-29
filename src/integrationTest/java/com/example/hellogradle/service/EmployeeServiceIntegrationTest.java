package com.example.hellogradle.service;


import com.example.hellogradle.dataTypeFactory.AdditionalPostgresDataTypeFactory;
import com.example.hellogradle.initializer.DatabaseContainerInitializer;
import com.example.model.entity.Employee;
import com.example.model.entity.Gender;
import com.example.service.impl.EmployeeServiceImpl;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE,
dataTypeFactoryClass = AdditionalPostgresDataTypeFactory.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class EmployeeServiceIntegrationTest extends DatabaseContainerInitializer {

    @Autowired
    private EmployeeServiceImpl helloService;
    Employee employee;
    @BeforeEach
    public void init(){
        employee=Employee.builder()
                .firstName("Artem")
                .lastName("Myachin")
                .dateOfBirth(LocalDate.parse("1997-06-25"))
                .departmentId(1L)
                .gender(Gender.MALE)
                .employeeId(1L)
                .jobTittle("sales")
                .build();
    }

    @Test
    @DataSet(value = {"dataset/init/employee/init.yml"},
            useSequenceFiltering = false)
    void getHello() {
        var expected = Collections.singletonList(employee);
        var actual = helloService.findAll();
        assertEquals(expected,actual);
    }
}