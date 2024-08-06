package com.procttor.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.procttor.api.model.Workspace;
import com.procttor.api.service.WorkspaceService;

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
}
