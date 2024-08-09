package com.procttor.api.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
        Optional<Workspace> findByUuid(UUID uuid);
        void deleteByUuid(UUID uuid);
}
