package com.procttor.api.service;

import java.util.List;
import java.util.Map;

import com.procttor.api.dto.UserDto;
import com.procttor.api.model.User;

public interface UserService {
    public UserDto createUser(User user);
    public List<UserDto>getAllUsers();
    public UserDto getUserByID(Long userId);
    public UserDto updateUser(Long id, Map<String, Object> updates);
    public void deleteUser(Long userId);
}
