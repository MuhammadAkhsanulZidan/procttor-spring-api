package com.procttor.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.UserWorkspaceDto;
import com.procttor.api.dto.WorkspaceDto;
import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.User;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.model.Workspace;
import com.procttor.api.repository.UserRepository;
import com.procttor.api.repository.UserWorkspaceRepository;
import com.procttor.api.repository.WorkspaceRepository;
import com.procttor.api.service.UserWorkspaceService;

@Service
public class UserWorkspaceServiceImpl implements UserWorkspaceService{

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserWorkspaceDto> getAllUserWorkspaces() {
        List<UserWorkspace>userWorkspaces=userWorkspaceRepository.findAll();
        List<UserWorkspaceDto> userWorkspaceDtos = new ArrayList<>();
        for(UserWorkspace userWorkspace: userWorkspaces){
            userWorkspaceDtos.add(mapToUserWorkspaceDto(userWorkspace));
        }
        return userWorkspaceDtos;
    }

    @Override
    public UserWorkspaceDto addUserToWorkspace(UUID workspaceUuid, UUID userUuid, String role) {        

        int roleId = 0;

        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
            .orElseThrow(()->new ResourceNotFoundException("Workspace not found"));
                
        User user = userRepository.findByUuid(userUuid)
            .orElseThrow(()->new ResourceNotFoundException("User not found"));
        
        Optional<UserWorkspace> existingLink = userWorkspaceRepository.findByUserIdAndWorkspaceId(user.getId(), workspace.getId());
        if (existingLink.isPresent()) {
            throw new IllegalArgumentException("User has already been linked to this workspace");
        }

        switch (role.toLowerCase()) {
            case "admin":
                roleId = 0;
                break;
            case "member":
                roleId = 1;
                break;
            default:
                roleId = -1;
                break; 
        }
        
        UserWorkspace userWorkspace = new UserWorkspace();
        userWorkspace.setUser(user);
        userWorkspace.setWorkspace(workspace);
        userWorkspace.setRoleId(roleId);

        UserWorkspace savedUserWorkspace = userWorkspaceRepository.save(userWorkspace);
        return mapToUserWorkspaceDto(savedUserWorkspace);
    }

    
    @Override
    public UserWorkspaceDto updateUserWorkspaceRole(UUID workspaceUuid, UUID userUuid, String role) {

        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
            .orElseThrow(()->new ResourceNotFoundException("Workspace not found"));
                
        User user = userRepository.findByUuid(userUuid)
            .orElseThrow(()->new ResourceNotFoundException("User not found"));
        
        UserWorkspace userWorkspace = userWorkspaceRepository.findByUserIdAndWorkspaceId(user.getId(), workspace.getId())
            .orElseThrow(()->new ResourceNotFoundException("User-Workspace not found"));

        int roleId = 0;
        switch (role.toLowerCase()) {
            case "admin":
                roleId = 0;
                break;
            case "member":
                roleId = 1;
                break;
            default:
                roleId = -1;
                break; 
        }
        
        userWorkspace.setRoleId(roleId);
        UserWorkspace updatedUserWorkspace = userWorkspaceRepository.save(userWorkspace);
        return mapToUserWorkspaceDto(updatedUserWorkspace);
    }

    @Override
    public void detachUserWorkspace(UUID userUuid, UUID workspaceUuid) {
        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
            .orElseThrow(()->new ResourceNotFoundException("Workspace not found"));
                
        User user = userRepository.findByUuid(userUuid)
            .orElseThrow(()->new ResourceNotFoundException("User not found"));
        
        userWorkspaceRepository.deleteByUserIdAndWorkspaceId(user.getId(), workspace.getId());
    }

    private UserWorkspaceDto mapToUserWorkspaceDto(UserWorkspace userWorkspace){
        UserWorkspaceDto userWorkspaceDto = 
            new UserWorkspaceDto(
                modelMapper.map(userWorkspace.getUser(), UserDto.class),
                modelMapper.map(userWorkspace.getWorkspace(),WorkspaceDto.class),
                userWorkspace.getRoleId());
        return userWorkspaceDto;
    }
}
