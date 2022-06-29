package com.example.service;

import com.example.model.entity.EntityUpdateStatistics;

public interface EntityUpdateStatisticsService {
    EntityUpdateStatistics getByEntityId(long entityId);
    EntityUpdateStatistics save(EntityUpdateStatistics entityUpdateStatistics);
    EntityUpdateStatistics update(long entityId);
}
