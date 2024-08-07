package com.procttor.api.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.procttor.api.model.User;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.repository.UserWorkspaceRepository;
import java.io.Serializable;
import java.util.Optional;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final UserWorkspaceRepository userWorkspaceRepository;
    // private final UserProjectRepository userProjectRepository; // Uncomment when UserProject is implemented

    public CustomPermissionEvaluator(UserWorkspaceRepository userWorkspaceRepository) {
        this.userWorkspaceRepository = userWorkspaceRepository;
        // this.userProjectRepository = userProjectRepository; // Uncomment when UserProject is implemented
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject instanceof Long && permission instanceof String) {
            Long targetId = (Long) targetDomainObject;
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
    public boolean hasRoleInWorkspace(Authentication authentication, Long workspaceId, int requiredRole) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser(); // Use method to access User object
        Optional<UserWorkspace> userWorkspace = userWorkspaceRepository.findByUserIdAndWorkspaceId(user.getId(), workspaceId);
        return userWorkspace.isPresent() && userWorkspace.get().getRoleId() == requiredRole;
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
