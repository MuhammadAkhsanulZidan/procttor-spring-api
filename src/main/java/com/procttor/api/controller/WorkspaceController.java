package com.procttor.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.dto.WorkspaceDto;
import com.procttor.api.model.Workspace;
import com.procttor.api.service.WorkspaceService;
import com.procttor.api.util.CustomPage;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceDto> createWorkspace(@RequestBody Workspace workspace) {
        WorkspaceDto savedWorkspace = workspaceService.createWorkspace(workspace);
        return new ResponseEntity<>(savedWorkspace, HttpStatus.OK);
    }

    @GetMapping("/{wid}")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ANY)")
    public ResponseEntity<WorkspaceDto> getWorkspaceById(@PathVariable UUID wid) {
        WorkspaceDto workspace = workspaceService.getWorkspaceByID(wid);
        return new ResponseEntity<>(workspace, HttpStatus.OK);
    }

    @GetMapping("/{wid}/users")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ANY)")
    public ResponseEntity<CustomPage<UserWithRoleDto>> getAllUsers(
        @PathVariable UUID wid,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        CustomPage<UserWithRoleDto> userDtos = workspaceService.getAllUsers(wid, page, size);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PatchMapping("/{wid}")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ADMIN)")
    public ResponseEntity<WorkspaceDto> patchUser(@PathVariable UUID wid, @RequestBody Map<String, Object> updates) {
        WorkspaceDto patchedWorkspace = workspaceService.updateWorkspace(wid, updates);
        return new ResponseEntity<>(patchedWorkspace, HttpStatus.OK);
    }

    @DeleteMapping("/{wid}")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ADMIN)")
    public ResponseEntity<String> deleteWorkspace(@PathVariable UUID wid) {
        workspaceService.deleteWorkspace(wid);
        return new ResponseEntity<>("Workspace successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/{wid}/users/search")
    @PreAuthorize("@customPermissionEvaluator.hasRoleInWorkspace(authentication, #wid, T(com.procttor.api.util.Role).ANY)")
    public ResponseEntity<List<UserWithRoleDto>> searchUsersByEmail(
            @PathVariable UUID wid,
            @RequestParam String email) {
        List<UserWithRoleDto> users = workspaceService.searchUsersByEmail(wid, email);
        return ResponseEntity.ok(users);
    }
}
