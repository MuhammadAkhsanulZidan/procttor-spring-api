package com.procttor.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.Workspace;
import com.procttor.api.repository.WorkspaceRepository;
import com.procttor.api.service.WorkspaceService;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Override
    public Workspace createWorkspace(Workspace workspace) {
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        return savedWorkspace;
    }

    @Override
    public List<Workspace> getAllWorkspaces() {
        List<Workspace>workspaces = workspaceRepository.findAll();
        return workspaces;
    }

    @Override
    public Workspace getWorkspaceByID(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                        .orElseThrow(()-> new ResourceNotFoundException("Workspace not found"));
        return workspace;
    }

    @Override
    public Workspace updateWorkspace(Long workspaceId, Map<String, Object> updates) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceId);
        if(optionalWorkspace.isPresent()){
            Workspace workspace = optionalWorkspace.get();
            updates.forEach((key, value)->{
                switch (key) {
                    case "name":
                        workspace.setName((String) value);
                        break;
                
                    case "description":
                        workspace.setDescription((String) value);
                        break;
                
                    case "image":
                        workspace.setImage((String) value);
                        break;

                    default:
                        break;
                }
            });

            Workspace patchedWorkspace = workspaceRepository.save(workspace);
            return patchedWorkspace;
        }
        else{
            throw new ResourceNotFoundException("Workspace not found");
        }
    }

    @Override
    public void deleteWorkspace(Long workspaceId) {
        workspaceRepository.deleteById(workspaceId);
    }
    
}
