package com.procttor.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.dto.UserWorkspaceDto;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.service.UserWorkspaceService;

@RestController
@RequestMapping("api/workspace-user")
public class UserWorkspaceController {
    
    @Autowired
    private UserWorkspaceService userWorkspaceService;
    
    @PostMapping
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #userWorkspaceDTO.workspaceId, T(com.procttor.api.util.Role).ADMIN)")
    public ResponseEntity<UserWorkspace> addUserToWorkspace(@RequestParam("id") String id, @RequestParam("add") String userId, @RequestParam("role") String role) {
        UserWorkspace userWorkspace = userWorkspaceService.addUserToWorkspace(id, userId, role);
        return new ResponseEntity<>(userWorkspace, HttpStatus.CREATED);
    }

    @PatchMapping
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #userWorkspaceDTO.workspaceId, Role.ADMIN)")
    public ResponseEntity<UserWorkspace> updateUserWorkspaceRole(@RequestParam("id") String id, @RequestParam("add") String userId, @RequestParam("role") String role) {
        UserWorkspace userWorkspace = userWorkspaceService.updateUserWorkspaceRole(id, userId, role);
        return new ResponseEntity<>(userWorkspace, HttpStatus.OK);
    }

}
