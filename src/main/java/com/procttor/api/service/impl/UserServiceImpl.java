package com.procttor.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.mapper.UserMapper;
import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.User;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.model.Workspace;
import com.procttor.api.repository.UserRepository;
import com.procttor.api.repository.UserWorkspaceRepository;
import com.procttor.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<UserDto> getAllUsers()  {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user:users){
            userDtos.add(UserMapper.mapToUserDto(user));
        }
        return userDtos;        
    }

    @Override
    public UserDto getUserByID(UUID uuid)  {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UUID uuid, Map<String, Object> updates) {
        Optional<User> optionalUser = userRepository.findByUuid(uuid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        user.setName((String) value);
                        break;
                    case "email":
                        user.setEmail((String) value);
                        break;
                    case "password":
                        user.setPassword(passwordEncoder.encode((String) value));
                        break;
                }
            });
            User patchedUser = userRepository.save(user);
            return modelMapper.map(patchedUser, UserDto.class);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public void deleteUser(UUID uuid)  {
        userRepository.deleteByUuid(uuid);
    }
    
    @Override
    public List<Workspace> getAllWorkspaces(UUID uuid) {
        User user = userRepository.findByUuid(uuid)
                    .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Long userId = user.getId();
        
        List<UserWorkspace> userWorkspaces = userWorkspaceRepository.findByUserId(userId);
        List<Workspace> workspaces = userWorkspaces.stream()
                .map(UserWorkspace::getWorkspace) 
                .collect(Collectors.toList());
        return workspaces; 
    }
}
