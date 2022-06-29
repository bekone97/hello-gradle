package com.example.service.impl;

import com.example.model.EntityUpdateStatisticsRepository;
import com.example.model.entity.EntityUpdateStatistics;
import com.example.service.EntityUpdateStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntityUpdateStatisticsServiceImpl implements EntityUpdateStatisticsService {

    private final EntityUpdateStatisticsRepository entityUpdateStatisticsRepository;

    @Override
    public EntityUpdateStatistics getByEntityId(long entityId) {
        return entityUpdateStatisticsRepository.findByEntityId(entityId)
                .orElseThrow(()->new RuntimeException("There is no statistics with such id "));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EntityUpdateStatistics save(EntityUpdateStatistics entityUpdateStatistics) {
        return entityUpdateStatisticsRepository.save(entityUpdateStatistics);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EntityUpdateStatistics update(long entityId) {
        var statistics = getByEntityId(entityId);
        var count =statistics.getUpdateCount();
        statistics.setUpdateCount(++count);
        return entityUpdateStatisticsRepository.save(statistics);
    }
}
