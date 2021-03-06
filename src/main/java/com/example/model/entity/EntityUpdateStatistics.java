package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "entity_update_statistics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityUpdateStatistics {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    //    @Column(name = "entity_name",unique = true,nullable = false)
//    private String entityName;
//
//    @Column(name = "entity_id", nullable = false)
//    private long entityId;

    @EmbeddedId
    private EntityUpdateStatisticsId entityUpdateStatisticsId;


    @Column(name = "update_count", nullable = false)
    private long updateCount;

}
