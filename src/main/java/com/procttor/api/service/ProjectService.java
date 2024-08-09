package com.procttor.api.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.procttor.api.dto.ProjectDto;
import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.model.Project;

public interface ProjectService {
    public ProjectDto createProject(UUID wid, Project project);
    public List<ProjectDto> getAllProjects(UUID wid);
    public ProjectDto getProjectByUuid(UUID uuid);
    public ProjectDto updateProject(UUID uuid, Map<String, Object> updates);
    public void deleteProject(UUID uuid);
    public List<UserWithRoleDto> getAllUsers(UUID uuid);
}
