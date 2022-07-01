package com.example.event.publisher;

import com.example.event.CustomEntityEvent;
import com.example.event.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomEntityEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEntityEvent(Object payload, Entity entity){
        applicationEventPublisher.publishEvent(new CustomEntityEvent(this,payload,entity));
    }
}
