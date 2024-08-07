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
        // Implement logic for handling permission based on targetDomainObject and permission
        if (targetDomainObject instanceof Long && permission instanceof String) {
            Long workspaceId = (Long) targetDomainObject;
            String perm = (String) permission;
            return hasRoleInWorkspace(authentication, workspaceId, Integer.parseInt(perm));
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // This can be used if you have specific logic for targetId and targetType
        // Uncomment and implement if you need to use targetId and targetType
        // if ("Project".equals(targetType)) {
        //     return hasRoleInProject(authentication, (Long) targetId, (String) permission);
        // }
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
