package com.procttor.api.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.procttor.api.model.Activity;
import com.procttor.api.model.Task;

public interface TaskService {
    public Task createTask(UUID activity_uuid, Task task);
    public List<Task> getAllTask(UUID activity_uuid);
    public Task getTaskById(Long task_id);
    public Task updateTask(Long task_id, Map<String, Object> updates);
    public void deleteTask(Long task_id);
}
