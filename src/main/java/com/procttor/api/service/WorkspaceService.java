package com.procttor.api.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.model.Workspace;
import com.procttor.api.util.CustomPage;

public interface WorkspaceService {
    public Workspace createWorkspace(Workspace workspace);
    public List<Workspace>getAllWorkspaces();
    public Workspace getWorkspaceByID(UUID workspaceId);
    public Workspace updateWorkspace(UUID workspaceId, Map<String, Object> updates);
    public void deleteWorkspace(UUID workspaceId);
    public CustomPage<UserWithRoleDto> getAllUsers(UUID workspaceId, int page, int size);
    public List<UserWithRoleDto> searchUsersByEmail(UUID workspaceId, String email);
}
