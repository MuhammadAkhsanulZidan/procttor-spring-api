package com.procttor.api.service;

import java.util.List;
import java.util.Map;

import com.procttor.api.dto.UserDto;
import com.procttor.api.model.User;
import com.procttor.api.model.Workspace;

public interface UserService {
    public User createUser(User user);
    public List<UserDto>getAllUsers();
    public List<Workspace>getAllWorkspaces(String uuid);
    public UserDto getUserByID(String uuid);
    public UserDto updateUser(String uuid, Map<String, Object> updates);
    public void deleteUser(String uuid);
}
