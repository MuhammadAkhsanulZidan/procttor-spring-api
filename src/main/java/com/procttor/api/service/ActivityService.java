package com.procttor.api.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.procttor.api.dto.ActivityDto;
import com.procttor.api.model.Activity;
import com.procttor.api.util.CustomPage;

public interface ActivityService {
    // public CustomPage<ActivityDto> getAllActivities(int page, int size);

    public ActivityDto createActivity(UUID projectUuid, Activity activity);
    public List<ActivityDto> getProjectActivities(UUID projectUuid);
    public ActivityDto getActivityByUuid(UUID activityUuid);
    public ActivityDto updateActivityByUuid(UUID activityUuid, Map<String, Object> updates);
    public void deleteActivityByUuid(UUID activityUuid);

}
