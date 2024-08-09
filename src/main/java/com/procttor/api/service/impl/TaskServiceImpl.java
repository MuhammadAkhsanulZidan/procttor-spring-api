package com.procttor.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procttor.api.exception.ResourceNotFoundException;
import com.procttor.api.model.Task;
import com.procttor.api.repository.ActivityRepository;
import com.procttor.api.repository.TaskRepository;
import com.procttor.api.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private TaskRepository taskRepository;

    // @Autowired
    // private ModelMapper modelMapper;

    @Override
    public Task createTask(UUID activity_uuid, Task task) {
        task.setActivity(activityRepository.findByUuid(activity_uuid)
            .orElseThrow(()->new ResourceNotFoundException(
                "activity not found")));
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    @Override
    public List<Task> getAllTask(UUID activity_uuid) {
        List<Task> tasks = activityRepository.findByUuid(activity_uuid)
            .orElseThrow(()->new ResourceNotFoundException(
                "activity not found")).getTasks();
        return tasks;        
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException(
                "task not found"));
        return task;
    }

    @Override
    public Task updateTask(Long task_id, Map<String, Object> updates) {
        Optional<Task> optionalTask = taskRepository.findById(task_id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            updates.forEach((key, value)->{
                switch (key) {
                    case "name":
                        task.setName((String) value);
                        break;
                    case "status":
                        task.setStatus((int) value);                
                    default:
                        break;
                }
            });
            taskRepository.save(task);
            return task;
        }
        else{
            throw new ResourceNotFoundException("Task not found");
        }
    }

    @Override
    public void deleteTask(Long task_id) {
        taskRepository.deleteById(task_id);
    }

}
