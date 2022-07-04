package com.example.hellogradle.service;


import com.example.hellogradle.dataTypeFactory.AdditionalPostgresDataTypeFactory;
import com.example.hellogradle.initializer.DatabaseContainerInitializer;
import com.example.model.entity.Employee;
import com.example.model.entity.Gender;
import com.example.service.EmployeeService;
import com.example.service.EntityUpdateStatisticsService;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.*;


@SpringBootTest
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE,
dataTypeFactoryClass = AdditionalPostgresDataTypeFactory.class)
@ActiveProfiles("test")
class EmployeeServiceIntegrationTest extends DatabaseContainerInitializer {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EntityUpdateStatisticsService entityUpdateStatisticsService;
    private Employee employee;
    @BeforeEach
    public void init(){
        employee=Employee.builder()
                .firstName("Artem")
                .lastName("Myachin")
                .dateOfBirth(LocalDate.parse("1997-06-25"))
                .departmentId(3L)
                .gender(Gender.MALE)
                .employeeId(1L)
                .jobTittle("sales")
                .deleted(false)
                .createdAt(LocalDateTime.of(2022,6,29,14,38,37,698672))
                .createdBy("admin")
                .modifiedAt(LocalDateTime.of(2022,6,29,14,38,37,698672))
                .modifiedBy("admin")
                .build();
    }


    @Test
    @DataSet(value = {"dataset/init/employee/init.yml","dataset/init/entity-update-statistics/init.yml"},useSequenceFiltering = false)
    @ExpectedDataSet(value = {"dataset/expected/entity-update-statistics/multithreading.yml"})
    void checkCount() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch  = new CountDownLatch(numberOfThreads);
        for (int i=0; i< numberOfThreads;i++){
            service.execute(()->{
                employeeService.update(employee,1);
                latch.countDown();
            });
        }
        latch.await();
        var executor=(ThreadPoolTaskExecutor)applicationContext.getBean("threadPoolTaskExecutor");
        while(executor.getThreadPoolExecutor().getCompletedTaskCount()<numberOfThreads){
            Thread.yield();
        }
        service.shutdown();
    }
}