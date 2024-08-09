package com.procttor.api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procttor.api.dto.ActivityDto;
import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.Activity;
import com.procttor.api.repository.ActivityRepository;
import com.procttor.api.repository.ProjectRepository;
import com.procttor.api.service.ActivityService;
import com.procttor.api.util.CustomPage;

@Service
public class ActivityServiceImpl implements ActivityService{

    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ActivityDto createActivity(UUID projectUuid, Activity activity) {
            // Print received data for debugging
        System.out.println("Received Activity: " + activity.getName());
        activity.setProject(projectRepository.findByUuid(projectUuid)
                                .orElseThrow(()-> new ResourceNotFoundException("project not found")));
        Activity savedActivity = activityRepository.save(activity);

        return modelMapper.map(savedActivity, ActivityDto.class);
    }

    @Override
    public List<ActivityDto> getProjectActivities(UUID projectUuid) {
        List<Activity> activities = activityRepository.findByProjectId(
            projectRepository.findByUuid(projectUuid)
                .orElseThrow(()-> new ResourceNotFoundException("project not found"))
                .getId()
        );
        List<ActivityDto> activityDtos = new ArrayList<>();
        for(Activity activity : activities){
            activityDtos.add(modelMapper.map(activity, ActivityDto.class));
        }

        return activityDtos;
    }

    @Override
    public ActivityDto getActivityByUuid(UUID activityUuid) {
        Activity activity = activityRepository.findByUuid(activityUuid)
                .orElseThrow(()->new ResourceNotFoundException("activity not found"));
        return modelMapper.map(activity, ActivityDto.class);
    }

    @Override
    public ActivityDto updateActivityByUuid(UUID activityUuid, Map<String, Object> updates) {
        Optional<Activity> optionalActivity = activityRepository.findByUuid(activityUuid);
        if(optionalActivity.isPresent()){
            Activity activity = optionalActivity.get();
            updates.forEach((key, value)->{
                switch(key){
                    case "name":
                        activity.setName((String) value);
                        break;
                    
                    case "description":
                        activity.setName((String) value);
                        break;
                    
                    case "url":
                        activity.setName((String) value);
                        break;
                    
                    case "plan_start_at":
                        activity.setPlanStartAt(LocalDate.parse((String) value));
                        break;

                    case "plan_end_at":
                        activity.setPlanEndAt(LocalDate.parse((String) value));
                        break;

                    case "start_at":
                        activity.setStartAt(LocalDate.parse((String) value));
                        break;

                    case "end_at":
                        activity.setEndAt(LocalDate.parse((String) value));
                        break;

                    case "status":
                        activity.setStatus((int) value);
                        break;
                }
            });
            activityRepository.save(activity);
            return modelMapper.map(activity, ActivityDto.class);
        } else{
            throw new ResourceNotFoundException("activity not found");
        }
    }

    @Override
    public void deleteActivityByUuid(UUID activityUuid) {
        activityRepository.deleteByUuid(activityUuid);
    }

}