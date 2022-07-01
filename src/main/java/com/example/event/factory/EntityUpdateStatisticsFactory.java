package com.example.event.factory;

import com.example.event.CustomEntityEvent;
import com.example.event.Entity;
import com.example.model.entity.EntityUpdateStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntityUpdateStatisticsFactory {

    private final Map<Entity,EntityType> entityContext;

    @Autowired
    public EntityUpdateStatisticsFactory(List<EntityType> entityTypes){
        entityContext=entityTypes.stream()
                .collect(Collectors.toMap(EntityType::getEntity, Function.identity()));

    }

    public EntityUpdateStatistics getEntityUpdateStatistics(CustomEntityEvent customEntityEvent){
        return entityContext.getOrDefault(customEntityEvent.getEntity(),(EntityType) new NoSuchEntityUpdateStatistics())
                .getEntityUpdateStatistics(customEntityEvent.getPayload());
    }

}
