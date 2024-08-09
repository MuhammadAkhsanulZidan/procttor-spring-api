package com.procttor.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.dto.ProjectDto;
import com.procttor.api.model.Activity;
import com.procttor.api.model.Project;
import com.procttor.api.service.ProjectService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/workspaces/{workspace_uuid}/projects")
    public ResponseEntity<List<ProjectDto>> getAllProject(@PathVariable(value = "workspace_uuid") UUID workspaceUuid) {
        List<ProjectDto> projects = projectService.getAllProjects(workspaceUuid);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    
    @PostMapping("/workspaces/{workspace_uuid}/projects")
    public ResponseEntity<ProjectDto> createProject(@PathVariable(value = "workspace_uuid") UUID workspaceUuid, @RequestBody Project project) {
        System.err.println("received activity: "+project.getName());
        ProjectDto savedProject = projectService.createProject(workspaceUuid, project);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    @GetMapping("/projects/{project_uuid}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable(value = "project_uuid") UUID projectUuid){
        ProjectDto savedProject = projectService.getProjectByUuid(projectUuid);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivities(@RequestBody Activity activity){
        System.err.println("received activity name: "+activity.getName());
        // Activity savedActivity= activityService.createActivity(projectUuid, activity);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }


    @PatchMapping("/projects/{project_uuid}")
    public ResponseEntity<ProjectDto> patchProject(@PathVariable(value = "project_uuid") UUID projectUuid, @RequestBody Map<String, Object> updates){
        ProjectDto patchedProject = projectService.updateProject(projectUuid, updates);
        return new ResponseEntity<>(patchedProject, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{project_uuid}")
    public ResponseEntity<String> deleteProject(@PathVariable(value = "project_uuid") UUID projectUuid, @RequestBody Map<String, Object> updates){
        projectService.deleteProject(projectUuid);
        return new ResponseEntity<>("Project succesfully deleted", HttpStatus.OK);
    }
}
