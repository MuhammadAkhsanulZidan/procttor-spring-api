package com.procttor.api.service;

import java.util.List;

import com.procttor.api.model.UserWorkspace;

public interface UserWorkspaceService {
    public List<UserWorkspace> getAllUserWorkspaces();
    public UserWorkspace addUserToWorkspace(String id, String userId, String role);
    public UserWorkspace updateUserWorkspaceRole(String id, String userId, String role);
    public void detachUserWorkspace(String id, String userId);
}
