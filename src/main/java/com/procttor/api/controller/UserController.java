package com.procttor.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.procttor.api.dto.UserDto;
import com.procttor.api.dto.WorkspaceDto;
import com.procttor.api.model.User;
import com.procttor.api.model.Workspace;
import com.procttor.api.security.CustomUserDetails;
import com.procttor.api.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByUuid(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UUID uuid = userDetails.getUuid();
        UserDto userDTO = userService.getUserByID(uuid);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);        
    }

    @GetMapping("/workspaces")
    public ResponseEntity<List<WorkspaceDto>> getAllUsers(@AuthenticationPrincipal CustomUserDetails userDetails){
        UUID uuid = userDetails.getUuid();
        List<WorkspaceDto> workspace = userService.getAllWorkspaces(uuid);
        return new ResponseEntity<>(workspace, HttpStatus.OK);
    }
    
    @PatchMapping
    public ResponseEntity<UserDto> patchUser(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Map<String, Object> updates){
        UUID uuid = userDetails.getUuid();
        UserDto patchedUser = userService.updateUser(uuid, updates);
        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
    }

}
