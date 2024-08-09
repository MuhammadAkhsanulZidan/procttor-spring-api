package com.procttor.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
    Optional<Activity> findByUuid(UUID uuid);
    List<Activity> findByProjectId(Long projectId);
    void deleteByUuid(UUID uuid);
}
