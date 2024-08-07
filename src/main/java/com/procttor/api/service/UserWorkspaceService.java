package com.procttor.api.service;

import java.util.List;

import com.procttor.api.dto.UserWorkspaceDto;
import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceService {
    public List<UserWorkspace> getAllUserWorkspaces();
    public UserWorkspace addUserToWorkspace(UserWorkspaceDto userWorkspaceDTO);
    public UserWorkspace updateUserWorkspaceRole(UserWorkspaceDto userWorkspaceDTO);
    public void detachUserWorkspace(Long userId, Long workspaceId);
}
