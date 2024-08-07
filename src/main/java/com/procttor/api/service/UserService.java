package com.procttor.api.service;

import java.util.List;
import java.util.Map;

import com.procttor.api.dto.UserDto;
import com.procttor.api.model.User;
import com.procttor.api.model.Workspace;

public interface UserService {
    public UserDto createUser(User user);
    public List<UserDto>getAllUsers();
    public List<Workspace>getAllWorkspaces(Long userId);
    public UserDto getUserByID(Long userId);
    public UserDto updateUser(Long id, Map<String, Object> updates);
    public void deleteUser(Long userId);
}
