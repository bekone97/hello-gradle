package com.example.service.impl;

import com.example.model.EntityUpdateStatisticsRepository;
import com.example.model.entity.Employee;
import com.example.model.entity.EntityUpdateStatistics;
import com.example.service.EntityUpdateStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntityUpdateStatisticsServiceImpl implements EntityUpdateStatisticsService {

    private final EntityUpdateStatisticsRepository entityUpdateStatisticsRepository;

    @Override
    public EntityUpdateStatistics getByEntityId(long entityId) {
        return entityUpdateStatisticsRepository.findByEntityId(entityId)
                .orElseThrow(() -> new RuntimeException("There is no statistics with such id "));
    }


    @Override
    public List<EntityUpdateStatistics> findAll() {
        return entityUpdateStatisticsRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EntityUpdateStatistics update(long entityId) {
        var statistics = getByEntityId(entityId);
        var count = statistics.getUpdateCount();
        statistics.setUpdateCount(++count);
        return entityUpdateStatisticsRepository.save(statistics);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void makeRecord(Employee employee) {
        log.info("Make save or update with Employee = {}",employee);
        entityUpdateStatisticsRepository.findByEntityId(employee.getEmployeeId())
                .ifPresentOrElse(entityUpdateStatistics -> {
                            var count = entityUpdateStatistics.getUpdateCount();
                            entityUpdateStatistics.setUpdateCount(++count);
                            entityUpdateStatisticsRepository.save(entityUpdateStatistics);
                        },
                        () -> entityUpdateStatisticsRepository.save(EntityUpdateStatistics.builder()
                                .entityName("Employee" + employee.getFirstName() + employee.getLastName() + UUID.randomUUID())
                                .entityId(employee.getEmployeeId())
                                .build()));
        }
    }

