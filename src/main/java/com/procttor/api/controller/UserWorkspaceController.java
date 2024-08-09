package com.procttor.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.dto.UserWorkspaceDto;
import com.procttor.api.service.UserWorkspaceService;

@RestController
@RequestMapping("api/workspaces")
public class UserWorkspaceController {
    
    @Autowired
    private UserWorkspaceService userWorkspaceService;
    
    @PostMapping("/{wid}/users/add")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ADMIN)")
    public ResponseEntity<UserWorkspaceDto> addUserToWorkspace(@PathVariable(value = "wid") UUID wid, @RequestParam("id") UUID userId, @RequestParam("role") String role) {
        UserWorkspaceDto userWorkspace = userWorkspaceService.addUserToWorkspace(
            wid, 
            userId, 
            role);
        
        return new ResponseEntity<>(userWorkspace, HttpStatus.CREATED);
    }

    @PatchMapping("/{wid}/users/edit")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ADMIN)")
    public ResponseEntity<UserWorkspaceDto> updateUserWorkspaceRole(@PathVariable(value = "wid") UUID wid, @RequestParam("id") UUID userId, @RequestParam("role") String role) {
        UserWorkspaceDto userWorkspace = userWorkspaceService.updateUserWorkspaceRole(
            wid, 
            userId, 
            role);
        return new ResponseEntity<>(userWorkspace, HttpStatus.OK);
    }
}
