package com.procttor.api.service;

import java.util.List;
import java.util.Map;

import com.procttor.api.model.Workspace;

public interface WorkspaceService {
    public Workspace createWorkspace(Workspace workspace);
    public List<Workspace>getAllWorkspaces();
    public Workspace getWorkspaceByID(Long workspaceId);
    public Workspace updateWorkspace(Long workspaceId, Map<String, Object> updates);
    public void deleteWorkspace(Long workspaceId);
}
