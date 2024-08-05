package com.procttor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

}
