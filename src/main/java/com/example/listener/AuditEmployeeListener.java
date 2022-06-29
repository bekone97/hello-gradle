package com.example.listener;

import com.example.model.entity.Employee;
import com.example.model.entity.EntityUpdateStatistics;
import com.example.service.EntityUpdateStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.UUID;

@Component
public class AuditEmployeeListener {

    private static EntityUpdateStatisticsService entityUpdateStatisticsService;



    @PostPersist
    public void makeNewRecord(Employee employee){
        entityUpdateStatisticsService.save(EntityUpdateStatistics.builder()
                        .entityName("Employee"+employee.getFirstName()+employee.getLastName()+ UUID.randomUUID())
                        .entityId(employee.getEmployeeId())
                .build());
    }

    @PostUpdate
    @PostRemove
    public void makeUpdateRecord(Employee employee){
        entityUpdateStatisticsService.update(employee.getEmployeeId());
    }

    @Autowired
    public void setEntityUpdateStatisticsService(EntityUpdateStatisticsService entityUpdateStatisticsService){
        this.entityUpdateStatisticsService=entityUpdateStatisticsService;
    }
}
