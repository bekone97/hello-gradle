package com.example.event.listener;

import com.example.event.CustomEntityEvent;
import org.springframework.context.ApplicationListener;

public class CustomEntityEventListener implements ApplicationListener<CustomEntityEvent> {

    @Override
    public void onApplicationEvent(CustomEntityEvent event) {
//        do nothing
    }
}
