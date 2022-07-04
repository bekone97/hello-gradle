package com.example.event.factory;

import com.example.event.Entity;
import com.example.model.entity.Employee;
import com.example.model.entity.EntityUpdateStatistics;
import com.example.model.entity.EntityUpdateStatisticsId;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEntityUpdateStatistic implements EntityType{
    private static final Entity ENTITY = Entity.EMPLOYEE;
    @Override
    public Entity getEntity() {
        return ENTITY;
    }

    @Override
    public EntityUpdateStatistics getEntityUpdateStatistics(Object payload) {
        var employee = (Employee) payload;
        return EntityUpdateStatistics.builder()
                .entityUpdateStatisticsId(EntityUpdateStatisticsId.builder()
                        .entityName(employee.getClass().getName() + employee.getEmployeeId())
                        .entityId(employee.getEmployeeId())
                        .build())
                .build();
    }
}
