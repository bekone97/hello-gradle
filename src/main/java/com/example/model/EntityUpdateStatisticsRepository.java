package com.example.model;

import com.example.model.entity.EntityUpdateStatistics;
import com.example.model.entity.EntityUpdateStatisticsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EntityUpdateStatisticsRepository extends JpaRepository<EntityUpdateStatistics, EntityUpdateStatisticsId> {

    @Modifying
    @Query(value = "UPDATE entity_update_statistics SET update_count=update_count+1 WHERE entity_id=(?1) AND entity_name=(?2)", nativeQuery = true)
    void increaseUpdateCountById(long entityId, String entityName);
}
