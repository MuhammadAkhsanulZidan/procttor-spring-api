package com.procttor.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserWorkspaceDto {
    @JsonProperty("user")
    private UserDto userDto;

    @JsonProperty("workspace")
    private WorkspaceDto workspaceDto;

    @JsonProperty("role_id")
    private int roleId;

    public UserWorkspaceDto(UserDto userDto, WorkspaceDto workspaceDto, int roleId){
        this.userDto = userDto;
        this.workspaceDto = workspaceDto;
        this.roleId = roleId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public WorkspaceDto getWorkspaceDto() {
        return workspaceDto;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setWorkspaceDto(WorkspaceDto workspaceDto) {
        this.workspaceDto = workspaceDto;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
