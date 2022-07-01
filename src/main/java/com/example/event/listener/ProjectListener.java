package com.example.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProjectListener {
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent contextRefreshedEvent){
        var context = contextRefreshedEvent.getApplicationContext();
        log.error("Spring version={} , Java version = {},Display name = {}, id = {}",SpringVersion.getVersion(),System.getProperty("java.version"),context.getDisplayName(),context.getId());
    }

    @EventListener
    public void handleContextStart(ContextStartedEvent contextStartedEvent){
        var context = contextStartedEvent.getApplicationContext();
        log.error("Spring version={} , Java version = {},Display name = {}, id = {}",SpringVersion.getVersion(),System.getProperty("java.version"),context.getDisplayName(),context.getId());

    }
}
