package com.example.service.impl;

import com.example.model.EntityUpdateStatisticsRepository;
import com.example.model.entity.EntityUpdateStatistics;
import com.example.model.entity.EntityUpdateStatisticsId;
import com.example.service.EntityUpdateStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntityUpdateStatisticsServiceImpl implements EntityUpdateStatisticsService {

    private final EntityUpdateStatisticsRepository entityUpdateStatisticsRepository;

    @Override
    public EntityUpdateStatistics getByEntityIdAndEntityName(long entityId, String entityName) {
        return entityUpdateStatisticsRepository.findById(EntityUpdateStatisticsId.builder()
                        .entityId(entityId)
                        .entityName(entityName)
                .build())
                .orElseThrow(() -> new RuntimeException("There is no statistics with such id "));
    }


    @Override
    public List<EntityUpdateStatistics> findAll() {
        return entityUpdateStatisticsRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(EntityUpdateStatistics entityUpdateStatistics) {
        log.info("Make save entityUpdateStatistics = {}",entityUpdateStatistics);
        entityUpdateStatisticsRepository.save(entityUpdateStatistics);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(EntityUpdateStatistics entityUpdateStatistics) {
        log.info("Make update entityUpdateStatistics = {}",entityUpdateStatistics);
        var entityUpdateStatisticsId = entityUpdateStatistics.getEntityUpdateStatisticsId();

        entityUpdateStatisticsRepository.increaseUpdateCountById(entityUpdateStatisticsId.getEntityId(),
                entityUpdateStatisticsId.getEntityName());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void makeRecord(EntityUpdateStatistics entityUpdateStatistics) {
        log.info("Make save or update with Employee = {}", entityUpdateStatistics);
        entityUpdateStatisticsRepository.findById(entityUpdateStatistics.getEntityUpdateStatisticsId())
                .ifPresentOrElse(existedEntityUpdateStatistics ->
                                entityUpdateStatisticsRepository.increaseUpdateCountById(entityUpdateStatistics.getEntityUpdateStatisticsId().getEntityId(),
                                        entityUpdateStatistics.getEntityUpdateStatisticsId().getEntityName()),
                        () -> entityUpdateStatisticsRepository.save(entityUpdateStatistics));
        }
    }

