package com.example.event.listener;

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
    public void handleTransaction(EntityUpdatedEvent entityUpdatedEvent){
        log.info("Got entityUpdatedEvent = {}",entityUpdatedEvent);
        entityUpdateStatisticsService.makeRecord(EntityUpdateStatistics.builder()
                        .entityUpdateStatisticsId(EntityUpdateStatisticsId.builder()
                                .entityName(entityUpdatedEvent.getEntityName())
                                .entityId(entityUpdatedEvent.getEntityId())
                                .build())
                .build());
    }
}
