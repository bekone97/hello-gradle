package com.example.event.factory;

import com.example.event.Entity;
import com.example.model.entity.EntityUpdateStatistics;

public class NoSuchEntityUpdateStatistics implements EntityType{
    @Override
    public Entity getEntity() {
        throw new RuntimeException("No such entity type");
    }

    @Override
    public EntityUpdateStatistics getEntityUpdateStatistics(Object payload) {
        return null;
    }
}
