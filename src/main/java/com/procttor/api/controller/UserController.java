package com.procttor.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.procttor.api.dto.UserDto;
import com.procttor.api.model.User;
import com.procttor.api.model.Workspace;
import com.procttor.api.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() throws Exception {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value="id") UUID id) throws Exception {
        UserDto userDTO = userService.getUserByID(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);        
    }

    @GetMapping("/{id}/workspaces")
    public ResponseEntity<List<Workspace>> getAllUsers(@PathVariable UUID id) throws Exception{
         List<Workspace> workspace = userService.getAllWorkspaces(id);
        return new ResponseEntity<>(workspace, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(@PathVariable UUID id, @RequestBody Map<String, Object> updates) throws Exception{
        UserDto patchedUser = userService.updateUser(id, updates);
        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) throws Exception {
        userService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
