package com.procttor.api.service.impl;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.procttor.api.dto.ProjectDto;
import com.procttor.api.dto.UserWithRoleDto;
import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.Project;
import com.procttor.api.model.User;
import com.procttor.api.model.UserProject;
import com.procttor.api.repository.ProjectRepository;
import com.procttor.api.repository.UserProjectRepository;
import com.procttor.api.repository.WorkspaceRepository;
import com.procttor.api.security.CustomUserDetails;
import com.procttor.api.service.ProjectService;
import com.procttor.api.util.Role;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectDto createProject(UUID wid, Project project) {
        project.setWorkspace(workspaceRepository.findByUuid(wid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "workspace not found")));
        Project savedProject = projectRepository.save(project);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();
            UserProject userProject = new UserProject();
            userProject.setUser(user);
            userProject.setProject(savedProject);
            userProject.setRoleId(Role.ADMIN);
            userProjectRepository.save(userProject);

        } else {
            throw new IllegalStateException("Unable to retrieve user details from authentication context");
        }

        return modelMapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getAllProjects(UUID wid) {
        List<Project> projects = workspaceRepository.findByUuid(wid)
                .orElseThrow(() -> new ResourceNotFoundException("workspace not found"))
                .getProjects();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects) {
            projectDtos.add(modelMapper.map(project, ProjectDto.class));
        }
        return projectDtos;
    }
    

    @Override
    public ProjectDto getProjectByUuid(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(UUID uuid, Map<String, Object> updates) {
        Optional<Project> optionalProject = projectRepository.findByUuid(uuid);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        project.setName((String) value);
                        break;
                    case "description":
                        project.setDescription((String) value);
                        break;
                    case "image":
                        project.setImage((String) value);
                        break;
                    case "plan_start_at":
                        if (value instanceof String) {
                            project.setPlanStartAt(LocalDate.parse((String) value));
                        }
                        break;
                    case "plan_end_at":
                        if (value instanceof String) {
                            project.setPlanEndAt(LocalDate.parse((String) value));
                        }
                        break;
                    case "start_at":
                        if (value instanceof String) {
                            project.setStartAt(LocalDate.parse((String) value));
                        }
                        break;
                    case "end_at":
                        if (value instanceof String) {
                            project.setEndAt(LocalDate.parse((String) value));
                        }
                        break;
                    case "status":
                        project.setStatus((int) value);
                        break;
                    default:
                        break;
                }
            });
            projectRepository.save(project);
            return modelMapper.map(project, ProjectDto.class);
        } else {
            throw new ResourceNotFoundException("Project not found");
        }
    }

    @Override
    public void deleteProject(UUID uuid) {
        projectRepository.deleteByUuid(uuid);
    }

    @Override
    public List<UserWithRoleDto> getAllUsers(UUID uuid) {
        Project project = projectRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        List<UserProject> userProject = userProjectRepository.findByProjectId(project.getId());

        return userProject.stream()
                .map(userWorkspace -> {
                    UserWithRoleDto userWithRoleDto = new UserWithRoleDto();
                    User user = userWorkspace.getUser();
                    userWithRoleDto.setUserId(user.getUuid());
                    userWithRoleDto.setName(user.getName());
                    userWithRoleDto.setEmail(user.getEmail());
                    userWithRoleDto.setRoleId(userWorkspace.getRoleId());
                    return userWithRoleDto; // Ensure the DTO is returned
                })
                .collect(Collectors.toList());
    }
}
