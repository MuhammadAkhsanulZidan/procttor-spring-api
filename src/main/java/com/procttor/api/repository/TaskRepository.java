package com.procttor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
