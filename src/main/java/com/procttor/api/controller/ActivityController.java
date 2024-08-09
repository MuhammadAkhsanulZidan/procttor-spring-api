package com.procttor.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.dto.ActivityDto;
import com.procttor.api.model.Activity;
import com.procttor.api.service.ActivityService;

@RestController
@RequestMapping("/api")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/projects/{project_uuid}/activities")
    public ResponseEntity<List<ActivityDto>> getProjectActivities(@PathVariable(value ="project_uuid") UUID projectUuid){
        List<ActivityDto> activities = activityService.getProjectActivities(projectUuid);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping("/projects/{project_uuid}/activities")
    public ResponseEntity<ActivityDto> createActivities(@PathVariable(value ="project_uuid") UUID projectUuid, @RequestBody Activity activity){
        ActivityDto savedActivity= activityService.createActivity(projectUuid, activity);
        return new ResponseEntity<>(savedActivity, HttpStatus.OK);
    }

    @GetMapping("/activities/{activity_uuid}")
    public ResponseEntity<ActivityDto> getActivity(@PathVariable(value ="activity_uuid") UUID activityUuid){
        ActivityDto savedActivity= activityService.getActivityByUuid(activityUuid);
        return new ResponseEntity<>(savedActivity, HttpStatus.OK);
    }

    @PatchMapping("/activities/{activity_uuid}")
    public ResponseEntity<ActivityDto> patchActivity(@PathVariable(value ="activity_uuid") UUID activityUuid, @RequestBody Map<String, Object> updates){
        ActivityDto patchedActivity= activityService.updateActivityByUuid(activityUuid, updates);
        return new ResponseEntity<>(patchedActivity, HttpStatus.OK);
    }

    @DeleteMapping("/activities/{activity_uuid}")
    public ResponseEntity<String> deleteActivity(@PathVariable(value ="activity_uuid") UUID activityUuid){
        activityService.deleteActivityByUuid(activityUuid);
        return new ResponseEntity<>("Activity succesfully deleted", HttpStatus.OK);
    }
}
