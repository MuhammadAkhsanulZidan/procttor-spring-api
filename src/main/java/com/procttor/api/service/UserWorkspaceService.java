package com.procttor.api.service;

import java.util.List;
import java.util.UUID;

import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceService {
    public List<UserWorkspace> getAllUserWorkspaces();
    public UserWorkspace addUserToWorkspace(UUID id, UUID userId, String role);
    public UserWorkspace updateUserWorkspaceRole(UUID id, UUID userId, String role);
    public void detachUserWorkspace(UUID id, UUID userId);
}
