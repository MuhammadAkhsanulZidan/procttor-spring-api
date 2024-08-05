package com.procttor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long>{

}
