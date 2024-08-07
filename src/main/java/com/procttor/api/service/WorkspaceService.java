package com.procttor.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.model.Workspace;
import com.procttor.api.util.CustomPage;

public interface WorkspaceService {
    public Workspace createWorkspace(Workspace workspace);
    public List<Workspace>getAllWorkspaces();
    public Workspace getWorkspaceByID(Long workspaceId);
    public Workspace updateWorkspace(Long workspaceId, Map<String, Object> updates);
    public void deleteWorkspace(Long workspaceId);
    public CustomPage<UserWithRoleDto> getAllUsers(Long workspaceId, int page, int size);
    public List<UserWithRoleDto> searchUsersByEmail(Long workspaceId, String email);
}
