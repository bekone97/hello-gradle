package com.example.event;


import lombok.Getter;
@Getter
public class CustomEntityEvent {

    private Object payload;

    private Entity entity;

    public CustomEntityEvent(Object payload,Entity entity) {
        this.payload=payload;
        this.entity=entity;
    }
}
