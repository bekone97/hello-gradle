package com.example.model;

import com.example.model.entity.EntityUpdateStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EntityUpdateStatisticsRepository extends JpaRepository<EntityUpdateStatistics,Long> {

    Optional<EntityUpdateStatistics> findByEntityIdAndEntityName(long entityId,String entityName);

    @Modifying
    @Query(value = "UPDATE entity_update_statistics SET update_count=update_count+1 WHERE id=(?1)", nativeQuery = true)
    void increaseUpdateCountById(long id);
}
