package com.procttor.api.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.User;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.model.Workspace;
import com.procttor.api.repository.UserWorkspaceRepository;
import com.procttor.api.repository.WorkspaceRepository;
import com.procttor.api.util.Role;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final UserWorkspaceRepository userWorkspaceRepository;
    private final WorkspaceRepository workspaceRepository;
    // private final UserProjectRepository userProjectRepository; // Uncomment when UserProject is implemented

    public CustomPermissionEvaluator(UserWorkspaceRepository userWorkspaceRepository, WorkspaceRepository workspaceRepository) {
        this.userWorkspaceRepository = userWorkspaceRepository;
        this.workspaceRepository = workspaceRepository;
        // this.userProjectRepository = userProjectRepository; // Uncomment when UserProject is implemented
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject instanceof Long && permission instanceof String) {
            UUID targetId = UUID.fromString((String) targetDomainObject);
            String perm = (String) permission;

            if (perm.startsWith("workspace:")) {
                int requiredRole = Integer.parseInt(perm.split(":")[1]);
                return hasRoleInWorkspace(authentication, targetId, requiredRole);
            } 
            
            // else if (perm.startsWith("project:")) {
            //     int requiredRole = Integer.parseInt(perm.split(":")[1]);
            //     return hasRoleInProject(authentication, targetId, requiredRole);
            // }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    // CustomPermissionEvaluator.java
    public boolean hasRoleInWorkspace(Authentication authentication, UUID workspaceUuid, int requiredRole) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser(); // Use method to access User object
        Workspace workspace = workspaceRepository.findByUuid(workspaceUuid)
                            .orElseThrow(()->new ResourceNotFoundException("Workspace not found"));        
        UserWorkspace userWorkspace = userWorkspaceRepository.findByUserIdAndWorkspaceId(user.getId(), workspace.getId())
                            .orElseThrow(()->new ResourceNotFoundException("Workspace not found"));
        if(requiredRole==Role.ANY){
            return true;
        }
        else{
            return userWorkspace.getRoleId() == requiredRole;
        }
    }


    // Uncomment and implement when UserProject functionality is needed
    /*
    public boolean hasRoleInProject(Authentication authentication, Long projectId, int requiredRole) {
        User user = (User) authentication.getPrincipal();
        Optional<UserProject> userProject = userProjectRepository.findByUserIdAndProjectId(user.getId(), projectId);
        return userProject.isPresent() && userProject.get().getRoleId() == requiredRole;
    }
    */
}
