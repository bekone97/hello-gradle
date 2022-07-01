package com.example.event;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class CustomEntityEvent extends ApplicationEvent {

    private Object payload;

    private Entity entity;

    public CustomEntityEvent(Object source,Object payload,Entity entity) {
        super(source);
        this.payload=payload;
        this.entity=entity;
    }
}
