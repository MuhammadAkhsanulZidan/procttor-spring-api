package com.procttor.api.service;

import java.util.List;
import java.util.Map;

import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.model.Workspace;
import com.procttor.api.util.CustomPage;

public interface WorkspaceService {
    public Workspace createWorkspace(Workspace workspace);
    public List<Workspace>getAllWorkspaces();
    public Workspace getWorkspaceByID(String workspaceId);
    public Workspace updateWorkspace(String workspaceId, Map<String, Object> updates);
    public void deleteWorkspace(String workspaceId);
    public CustomPage<UserWithRoleDto> getAllUsers(String workspaceId, int page, int size);
    public List<UserWithRoleDto> searchUsersByEmail(String workspaceId, String email);
}
