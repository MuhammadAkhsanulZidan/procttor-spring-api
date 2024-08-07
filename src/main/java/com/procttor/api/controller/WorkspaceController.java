package com.procttor.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.model.Workspace;
import com.procttor.api.service.WorkspaceService;
import com.procttor.api.util.CustomPage;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        return new ResponseEntity<>(workspaces, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        Workspace savedWorkspace = workspaceService.createWorkspace(workspace);
        return new ResponseEntity<>(savedWorkspace, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable Long id) {
        Workspace workspace = workspaceService.getWorkspaceByID(id);
        return new ResponseEntity<>(workspace, HttpStatus.OK);
    }

    @GetMapping("/{workspaceId}/users")
    public ResponseEntity<CustomPage<UserWithRoleDto>> getAllUsers(
        @PathVariable Long workspaceId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        CustomPage<UserWithRoleDto> userDtos = workspaceService.getAllUsers(workspaceId, page, size);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Workspace> patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Workspace patchedWorkspace = workspaceService.updateWorkspace(id, updates);
        return new ResponseEntity<>(patchedWorkspace, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkspace(@PathVariable Long id) {
        workspaceService.deleteWorkspace(id);
        return new ResponseEntity<>("Workspace successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/{workspaceId}/users/search")
    public ResponseEntity<List<UserWithRoleDto>> searchUsersByEmail(
            @PathVariable Long workspaceId,
            @RequestParam String email) {

        List<UserWithRoleDto> users = workspaceService.searchUsersByEmail(workspaceId, email);
        return ResponseEntity.ok(users);
    }

}
