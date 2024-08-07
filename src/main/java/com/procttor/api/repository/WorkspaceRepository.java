package com.procttor.api.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
        Optional<Workspace> findByUuid(String uuid);
        void deleteByUuid(String uuid);
}
