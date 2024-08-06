package com.procttor.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserWorkspaceDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("workspace_id")
    private Long workspaceId;

    @JsonProperty("role_id")
    private int roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
