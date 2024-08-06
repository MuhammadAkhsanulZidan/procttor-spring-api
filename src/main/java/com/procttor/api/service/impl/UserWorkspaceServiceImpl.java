package com.procttor.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procttor.api.dto.UserWorkspaceDTO;
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
    public UserWorkspace addUserToWorkspace(UserWorkspaceDTO userWorkspaceDTO) {
        Long userId = userWorkspaceDTO.getUserId();
        Long workspaceId = userWorkspaceDTO.getWorkspaceId();
        int roleId = userWorkspaceDTO.getRoleId();

        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceId);

        if (!optionalWorkspace.isPresent()) {
            throw new ResourceNotFoundException("Workspace not found");
        }
        
        Workspace workspace = optionalWorkspace.get();
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }

        Optional<UserWorkspace> existingLink = userWorkspaceRepository.findByUserIdAndWorkspaceId(userId, workspaceId);
        if (existingLink.isPresent()) {
            throw new RuntimeException("User has already been linked to this workspace");
        }

        User user = optionalUser.get();
        UserWorkspace userWorkspace = new UserWorkspace();
        userWorkspace.setUser(user);
        userWorkspace.setWorkspace(workspace);
        userWorkspace.setRoleId(roleId);

        UserWorkspace savedUserWorkspace = userWorkspaceRepository.save(userWorkspace);
        return savedUserWorkspace;
    }

    
    @Override
    public UserWorkspace updateUserWorkspaceRole(UserWorkspaceDTO userWorkspaceDTO) {
        Optional<UserWorkspace> userWorkspaceOpt = userWorkspaceRepository.findByUserIdAndWorkspaceId(userWorkspaceDTO.getUserId(), userWorkspaceDTO.getWorkspaceId());

        if (userWorkspaceOpt.isPresent()) {
            UserWorkspace userWorkspace = userWorkspaceOpt.get();
            userWorkspace.setRoleId(userWorkspaceDTO.getRoleId());
            UserWorkspace updatedUserWorkspace = userWorkspaceRepository.save(userWorkspace);
            return updatedUserWorkspace;
        } else {
            throw new ResourceNotFoundException("UserWorkspace not found");
        }
    }

    @Override
    public void detachUserWorkspace(Long userId, Long workspaceId) {
        userWorkspaceRepository.deleteByUserIdAndWorkspaceId(userId, workspaceId);
    }
}
