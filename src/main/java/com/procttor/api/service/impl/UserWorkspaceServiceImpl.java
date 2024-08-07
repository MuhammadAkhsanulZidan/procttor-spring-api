package com.procttor.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procttor.api.dto.UserWorkspaceDto;
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

    @Override
    public List<UserWorkspace> getAllUserWorkspaces() {
        List<UserWorkspace>userWorkspaces=userWorkspaceRepository.findAll();
        return userWorkspaces;
    }

    @Override
    public UserWorkspace addUserToWorkspace(UUID workspaceUuid, UUID userUuid, String role) {        

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
        return savedUserWorkspace;
    }

    
    @Override
    public UserWorkspace updateUserWorkspaceRole(UUID workspaceUuid, UUID userUuid, String role) {

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
        return updatedUserWorkspace;
    }

    @Override
    public void detachUserWorkspace(UUID userUuid, UUID workspaceUuid) {
        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
            .orElseThrow(()->new ResourceNotFoundException("Workspace not found"));
                
        User user = userRepository.findByUuid(userUuid)
            .orElseThrow(()->new ResourceNotFoundException("User not found"));
        
        userWorkspaceRepository.deleteByUserIdAndWorkspaceId(user.getId(), workspace.getId());
    }
}
