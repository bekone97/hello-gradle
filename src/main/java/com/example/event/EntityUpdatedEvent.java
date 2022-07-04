package com.example.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityUpdatedEvent {
    private long entityId;
    private String entityName;
}
