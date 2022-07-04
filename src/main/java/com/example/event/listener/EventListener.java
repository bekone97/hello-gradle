package com.example.event.listener;

import com.example.event.EntityCreatedEvent;
import com.example.event.EntityUpdatedEvent;
import com.example.model.entity.EntityUpdateStatistics;
import com.example.model.entity.EntityUpdateStatisticsId;
import com.example.service.EntityUpdateStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final EntityUpdateStatisticsService entityUpdateStatisticsService;


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUpdateTransaction(EntityUpdatedEvent entityUpdatedEvent){
        log.info("Got entityUpdatedEvent = {}",entityUpdatedEvent);
        entityUpdateStatisticsService.update(EntityUpdateStatistics.builder()
                        .entityUpdateStatisticsId(EntityUpdateStatisticsId.builder()
                                .entityName(entityUpdatedEvent.getEntityName())
                                .entityId(entityUpdatedEvent.getEntityId())
                                .build())
                .build());
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCreateTransaction(EntityCreatedEvent entityCreatedEvent){
        log.info("Got entityCreatedEvent = {}",entityCreatedEvent);
        entityUpdateStatisticsService.save(EntityUpdateStatistics.builder()
                .entityUpdateStatisticsId(EntityUpdateStatisticsId.builder()
                        .entityName(entityCreatedEvent.getEntityName())
                        .entityId(entityCreatedEvent.getEntityId())
                        .build())
                .build());
    }
}
