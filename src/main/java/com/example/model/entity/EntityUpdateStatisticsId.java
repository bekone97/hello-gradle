package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityUpdateStatisticsId implements Serializable {

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_id")
    private long entityId;
}
