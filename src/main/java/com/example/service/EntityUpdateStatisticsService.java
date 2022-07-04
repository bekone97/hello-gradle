package com.example.service;

import com.example.model.entity.EntityUpdateStatistics;

import java.util.List;

public interface EntityUpdateStatisticsService {
    EntityUpdateStatistics getByEntityIdAndEntityName(long entityId, String entityName);
    void makeRecord(EntityUpdateStatistics entityUpdateStatistics);
    List<EntityUpdateStatistics> findAll();
    void save(EntityUpdateStatistics entityUpdateStatistics);
    void update(EntityUpdateStatistics entityUpdateStatistics);
}
