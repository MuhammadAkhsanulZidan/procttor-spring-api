package com.procttor.api.service;

import java.util.List;
import java.util.UUID;

import com.procttor.api.dto.UserWorkspaceDto;
import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceService {
    public List<UserWorkspaceDto> getAllUserWorkspaces();
    public UserWorkspaceDto addUserToWorkspace(UUID id, UUID userId, String role);
    public UserWorkspaceDto updateUserWorkspaceRole(UUID id, UUID userId, String role);
    public void detachUserWorkspace(UUID id, UUID userId);
}
