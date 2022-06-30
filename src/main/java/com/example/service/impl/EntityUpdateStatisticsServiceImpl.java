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

@Service
@RequiredArgsConstructor
@Slf4j
public class EntityUpdateStatisticsServiceImpl implements EntityUpdateStatisticsService {

    private final EntityUpdateStatisticsRepository entityUpdateStatisticsRepository;

    @Override
    public EntityUpdateStatistics getByEntityIdAndEntityName(long entityId, String entityName) {
        return entityUpdateStatisticsRepository.findByEntityIdAndEntityName(entityId,entityName)
                .orElseThrow(() -> new RuntimeException("There is no statistics with such id "));
    }


    @Override
    public List<EntityUpdateStatistics> findAll() {
        return entityUpdateStatisticsRepository.findAll();
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void makeRecord(Employee employee) {
        log.info("Make save or update with Employee = {}",employee);
        entityUpdateStatisticsRepository.findByEntityIdAndEntityName(employee.getEmployeeId(),employee.getClass().getName())
                .ifPresentOrElse(entityUpdateStatistics -> entityUpdateStatisticsRepository.increaseUpdateCountById(entityUpdateStatistics.getId()),
                        () -> entityUpdateStatisticsRepository.save(EntityUpdateStatistics.builder()
                                .entityName(employee.getClass().getName())
                                .entityId(employee.getEmployeeId())
                                .build()));
        }
    }

