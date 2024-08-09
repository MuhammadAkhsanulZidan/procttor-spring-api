package com.procttor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long>{
    Optional<UserProject> findByUserIdAndProjectId(Long userId, Long projectId);
    List<UserProject> findByUserId(Long userId);
    List<UserProject> findByProjectId(Long projectId);
    void deleteByUserIdAndProjectId(Long userId, Long projectId); 
}
