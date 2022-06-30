package com.example.listener;

import com.example.model.entity.Employee;
import com.example.service.EntityUpdateStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuditEmployeeListener {

    private final EntityUpdateStatisticsService entityUpdateStatisticsService;


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleTransaction(Employee employee){
        log.info("Got event about create or update Employee = {}",employee);
            entityUpdateStatisticsService.makeRecord(employee);
    }
}
