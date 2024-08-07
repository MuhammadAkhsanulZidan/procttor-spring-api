package com.procttor.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.dto.UserWorkspaceDto;
import com.procttor.api.model.User;
import com.procttor.api.model.UserWorkspace;
import com.procttor.api.model.Workspace;
import com.procttor.api.repository.UserRepository;
import com.procttor.api.repository.UserWorkspaceRepository;
import com.procttor.api.repository.WorkspaceRepository;
import com.procttor.api.service.UserWorkspaceService;

@RestController
@RequestMapping("api/workspace-user")
public class UserWorkspaceController {
    
    @Autowired
    private UserWorkspaceService userWorkspaceService;

    // @GetMapping
    // public ResponseEntity<List<UserWorkspace>> getAllUserWorkspaces() {
    //     List<UserWorkspace> userWorkspaces = userWorkspaceService.getAllUserWorkspaces();
    //     return new ResponseEntity<>(userWorkspaces, HttpStatus.OK);
    // }
    
    @PostMapping
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #userWorkspaceDTO.workspaceId, 1)")
    public ResponseEntity<UserWorkspace> addUserToWorkspace(@RequestParam("workspace-id") Long id, @RequestBody UserWorkspaceDto userWorkspaceDTO) {
        userWorkspaceDTO.setWorkspaceId(id);
        UserWorkspace userWorkspace = userWorkspaceService.addUserToWorkspace(userWorkspaceDTO);
        return new ResponseEntity<>(userWorkspace, HttpStatus.CREATED);
    }

    @PatchMapping
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #userWorkspaceDTO.workspaceId, 1)")
    public ResponseEntity<UserWorkspace> updateUserWorkspaceRole(@RequestBody UserWorkspaceDto userWorkspaceDTO) {
        UserWorkspace userWorkspace = userWorkspaceService.updateUserWorkspaceRole(userWorkspaceDTO);
        return new ResponseEntity<>(userWorkspace, HttpStatus.OK);
    }

}
