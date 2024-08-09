package com.procttor.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procttor.api.model.Task;
import com.procttor.api.service.TaskService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/activities/{activity_uuid}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable(value="activity_uuid") UUID activityUuid) {
        List<Task> tasks = taskService.getAllTask(activityUuid);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/activities/{activity_uuid}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable(value="activity_uuid") UUID activityUuid, @RequestBody Task task) {
        Task savedTask = taskService.createTask(activityUuid, task);
        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

    @PatchMapping("/activities/{activity_uuid}/tasks")
    public ResponseEntity<Task> patchTask(@PathVariable(value="task_id") Long taskId, @RequestBody Map<String, Object> updates) {
        Task savedTask = taskService.updateTask(taskId, updates);
        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable(value="task_id") Long taskId, @RequestBody Map<String, Object> updates) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task succesfully deleted", HttpStatus.OK);
    }
}
