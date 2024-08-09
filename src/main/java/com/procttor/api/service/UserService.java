package com.procttor.api.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.WorkspaceDto;
import com.procttor.api.model.User;
import com.procttor.api.model.Workspace;

public interface UserService {
    public UserDto createUser(User user);
    public List<UserDto>getAllUsers(int page, int size);
    public List<WorkspaceDto>getAllWorkspaces(UUID uuid);
    public UserDto getUserByID(UUID uuid);
    public UserDto updateUser(UUID uuid, Map<String, Object> updates);
    public void deleteUser(UUID uuid);
}
