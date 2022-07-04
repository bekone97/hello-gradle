package com.example.event.listener;

import com.example.event.CustomEntityEvent;
import com.example.event.factory.EntityUpdateStatisticsFactory;
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
    private final EntityUpdateStatisticsFactory entityUpdateStatisticsFactory;


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransaction(CustomEntityEvent customEntityEvent){
        log.info("Got customEntityEvent = {}",customEntityEvent);
        entityUpdateStatisticsService.makeRecord(entityUpdateStatisticsFactory.getEntityUpdateStatistics(customEntityEvent));
    }
}
