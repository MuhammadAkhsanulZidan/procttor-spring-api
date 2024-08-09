package com.procttor.api.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserWithRoleDto {

    @JsonProperty("id")
    private UUID userId;

    @JsonProperty("name")
    private String name; 

    @JsonProperty("email")
    private String email;

    @JsonProperty("role_id")
    private int roleId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
