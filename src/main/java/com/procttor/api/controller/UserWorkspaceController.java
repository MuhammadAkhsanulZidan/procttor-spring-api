package com.procttor.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.model.UserWorkspace;
import com.procttor.api.repository.UserWorkspaceRepository;

@RestController
@RequestMapping("api/user-workspace")
public class UserWorkspaceController {
    
    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @GetMapping
    public List<UserWorkspace> getAllUserWorkspaces() {
        return userWorkspaceRepository.findAll();
    }

    @PostMapping
    public UserWorkspace createUserWorkspace(@RequestBody UserWorkspace userWorkspace) {
        return userWorkspaceRepository.save(userWorkspace);
    }
}
