package com.example.event.factory;

import com.example.event.Entity;
import com.example.model.entity.EntityUpdateStatistics;

public interface EntityType {

    Entity getEntity();

    EntityUpdateStatistics getEntityUpdateStatistics(Object payload);
}
