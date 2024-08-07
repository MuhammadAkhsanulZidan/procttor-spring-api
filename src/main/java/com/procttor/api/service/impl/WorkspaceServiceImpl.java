package com.procttor.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.User;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.model.Workspace;
import com.procttor.api.repository.UserWorkspaceRepository;
import com.procttor.api.repository.WorkspaceRepository;
import com.procttor.api.security.CustomUserDetails;
import com.procttor.api.service.WorkspaceService;
import com.procttor.api.util.Role;
import com.procttor.api.util.CustomPage;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Workspace createWorkspace(Workspace workspace) {

        Workspace savedWorkspace = workspaceRepository.save(workspace);
    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser(); 
        UserWorkspace userWorkspace = new UserWorkspace();
        userWorkspace.setUser(user);
        userWorkspace.setWorkspace(savedWorkspace);
        userWorkspace.setRoleId(Role.ADMIN); 
        userWorkspaceRepository.save(userWorkspace);
    } else {
        throw new IllegalStateException("Unable to retrieve user details from authentication context");
    }
        return savedWorkspace;
    }

    @Override
    public List<Workspace> getAllWorkspaces() {
        List<Workspace>workspaces = workspaceRepository.findAll();
        return workspaces;
    }

    @Override
    public Workspace getWorkspaceByID(String workspaceUuid) {
        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
                        .orElseThrow(()-> new ResourceNotFoundException("Workspace not found"));
        return workspace;
    }

    @Override
    public Workspace updateWorkspace(String workspaceUuid, Map<String, Object> updates) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findByUuid(workspaceUuid);
        if(optionalWorkspace.isPresent()){
            Workspace workspace = optionalWorkspace.get();
            updates.forEach((key, value)->{
                switch (key) {
                    case "name":
                        workspace.setName((String) value);
                        break;
                
                    case "description":
                        workspace.setDescription((String) value);
                        break;
                
                    case "image":
                        workspace.setImage((String) value);
                        break;

                    default:
                        break;
                }
            });

            Workspace patchedWorkspace = workspaceRepository.save(workspace);
            return patchedWorkspace;
        }
        else{
            throw new ResourceNotFoundException("Workspace not found");
        }
    }

    @Override
    public void deleteWorkspace(String workspaceUuid) {
        workspaceRepository.deleteByUuid(workspaceUuid);
    }

    @Override
    public CustomPage<UserWithRoleDto> getAllUsers(String workspaceUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("roleId").ascending().and(Sort.by("user.id").ascending()));
        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
                        .orElseThrow(()-> new ResourceNotFoundException("Workspace not found"));
        
        Page<UserWorkspace> userWorkspaces = userWorkspaceRepository.findByWorkspaceId(workspace.getId(), pageable);

        List<UserWithRoleDto> content = userWorkspaces.getContent().stream()
        .map(userWorkspace -> {
            UserWithRoleDto userWithRoleDto = new UserWithRoleDto();
            User user = userWorkspace.getUser();
            userWithRoleDto.setUserId(user.getId());
            userWithRoleDto.setName(user.getName());
            userWithRoleDto.setEmail(user.getEmail());
            userWithRoleDto.setRoleId(userWorkspace.getRoleId());
            return userWithRoleDto;  // Ensure the DTO is returned
        })
        .collect(Collectors.toList());

        return new CustomPage<>(content, userWorkspaces.getTotalElements(), userWorkspaces.getTotalPages());
    }

    public List<UserWithRoleDto> searchUsersByEmail(String workspaceUuid, String email) {
        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
                        .orElseThrow(()-> new ResourceNotFoundException("Workspace not found"));
        List<UserWorkspace> userWorkspaces = userWorkspaceRepository.findByWorkspaceIdAndUserEmailContaining(workspace.getId(), email);

        if (userWorkspaces.isEmpty()) {
            throw new ResourceNotFoundException("No users found in the workspace with email containing: " + email);
        }

        return userWorkspaces.stream().map(userWorkspace -> {
            UserWithRoleDto userWithRoleDto = new UserWithRoleDto();
            userWithRoleDto.setUserId(userWorkspace.getUser().getId());
            userWithRoleDto.setName(userWorkspace.getUser().getName());
            userWithRoleDto.setEmail(userWorkspace.getUser().getEmail());
            userWithRoleDto.setRoleId(userWorkspace.getRoleId());
            return userWithRoleDto;
        }).collect(Collectors.toList());
    }
}
