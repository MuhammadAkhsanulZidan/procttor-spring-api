package com.procttor.api.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long>{
    Optional<UserWorkspace> findByUserIdAndWorkspaceId(Long userId, Long workspaceId);
    List<UserWorkspace> findByUserId(Long userId);
    Page<UserWorkspace> findByWorkspaceId(Long workspaceId, Pageable pageable);
    void deleteByUserIdAndWorkspaceId(Long userId, Long workspaceId); 

    @Query("SELECT uw FROM UserWorkspace uw WHERE uw.workspace.id = :workspaceId AND LOWER(uw.user.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<UserWorkspace> findByWorkspaceIdAndUserEmailContaining(@Param("workspaceId") Long workspaceId, @Param("email") String email);

}
