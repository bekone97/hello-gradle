package com.example.model;

import com.example.model.entity.EntityUpdateStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntityUpdateStatisticsRepository extends JpaRepository<EntityUpdateStatistics,Long> {
    Optional<EntityUpdateStatistics> findByEntityId(long entityId);
}
