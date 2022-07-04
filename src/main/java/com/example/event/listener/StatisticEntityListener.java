package com.example.event.listener;

import com.example.event.EntityCreatedEvent;
import com.example.event.EntityUpdatedEvent;
import com.example.event.EventInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;


public class StatisticEntityListener {

    private static ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        StatisticEntityListener.applicationEventPublisher = applicationEventPublisher;
    }

    @PostUpdate
    public void entityUpdate(Object entityInformation){
        var eventInformation= (EventInformation) entityInformation;
        applicationEventPublisher.publishEvent(EntityUpdatedEvent.builder()
                        .entityId(eventInformation.getEventId())
                        .entityName(eventInformation.getEventName())
                .build());
    }

    @PostPersist
    public void entityPersist(Object entityInformation){
        var eventInformation= (EventInformation) entityInformation;
        applicationEventPublisher.publishEvent(EntityCreatedEvent.builder()
                .entityId(eventInformation.getEventId())
                .entityName(eventInformation.getEventName())
                .build());
    }
}
