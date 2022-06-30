package com.example.service;

import com.example.model.entity.Employee;
import com.example.model.entity.EntityUpdateStatistics;

import java.util.List;

public interface EntityUpdateStatisticsService {
    EntityUpdateStatistics getByEntityIdAndEntityName(long entityId, String entityName);
    void makeRecord(Employee employee);
    List<EntityUpdateStatistics> findAll();
}
