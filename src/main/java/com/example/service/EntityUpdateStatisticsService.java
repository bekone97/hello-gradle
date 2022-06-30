package com.example.service;

import com.example.model.entity.Employee;
import com.example.model.entity.EntityUpdateStatistics;

import java.util.List;

public interface EntityUpdateStatisticsService {
    EntityUpdateStatistics getByEntityId(long entityId);
    EntityUpdateStatistics update(long entityId);
    void makeRecord(Employee employee);
    List<EntityUpdateStatistics> findAll();
}
