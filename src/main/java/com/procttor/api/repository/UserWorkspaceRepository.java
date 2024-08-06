package com.procttor.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long>{
    Optional<UserWorkspace> findByUserIdAndWorkspaceId(Long userId, Long workspaceId);
    void deleteByUserIdAndWorkspaceId(Long userId, Long workspaceId); 
}
