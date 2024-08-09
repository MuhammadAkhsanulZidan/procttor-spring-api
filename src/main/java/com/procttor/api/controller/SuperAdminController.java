package com.procttor.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.WorkspaceDto;
import com.procttor.api.service.UserService;
import com.procttor.api.service.UserWorkspaceService;
import com.procttor.api.service.WorkspaceService;
import com.procttor.api.util.CustomPage;

@RestController
@RequestMapping("/api/admin")
public class SuperAdminController {
    
    @Autowired
    private UserService userService;


    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private UserWorkspaceService userWorkspaceService;


    // user 
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size
    ) {
        List<UserDto> users = userService.getAllUsers(page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable(value="id") UUID id) {
        UserDto userDTO = userService.getUserByID(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);        
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    // workspace
    @GetMapping("/workspaces")
    public ResponseEntity<CustomPage<WorkspaceDto>> getAllWorkspaces(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size) {
        
        CustomPage<WorkspaceDto> workspaces = workspaceService.getAllWorkspaces(page, size);
        return new ResponseEntity<>(workspaces, HttpStatus.OK);
    }

    @GetMapping("/workspaces/{id}")
    public ResponseEntity<WorkspaceDto> getWorkspaceById(@PathVariable UUID id) {
        WorkspaceDto workspace = workspaceService.getWorkspaceByID(id);
        return new ResponseEntity<>(workspace, HttpStatus.OK);
    }

}
