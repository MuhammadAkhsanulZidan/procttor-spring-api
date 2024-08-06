package com.procttor.api.service;

import java.util.List;

import com.procttor.api.dto.UserWorkspaceDTO;
import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceService {
    public List<UserWorkspace> getAllUserWorkspaces();
    public UserWorkspace addUserToWorkspace(UserWorkspaceDTO userWorkspaceDTO);
    public UserWorkspace updateUserWorkspaceRole(UserWorkspaceDTO userWorkspaceDTO);
    public void detachUserWorkspace(Long userId, Long workspaceId);
}
