package com.procttor.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findByWorkspaceId(Long workspaceId);
    Optional<Project> findByIdAndWorkspaceId(Long id, Long workspaceId);
    Optional<Project> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);    
}
