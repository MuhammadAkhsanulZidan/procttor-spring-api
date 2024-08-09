package com.procttor.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name= "[activity]")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonProperty("name")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "url", nullable = true)
    private String url;

    @JsonProperty("plan_start_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "plan_start_at", nullable = true)
    private LocalDate planStartAt;

    @JsonProperty("plan_end_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "plan_end_at", nullable = true)
    private LocalDate planEndAt;

    @JsonProperty("start_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_at", nullable = true)
    private LocalDate startAt;

    @JsonProperty("end_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_at", nullable = true)
    private LocalDate endAt;

    @Column(name = "status", nullable = false)
    private int status;

    @OneToMany(mappedBy="activity", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Activity(){
        this.uuid = UUID.randomUUID();
    }

    public Activity(UUID uuid, 
    String name, 
    String description,
    String url,
    LocalDate planStartAt,
    LocalDate planEndAt,
    LocalDate startAt,
    LocalDate endAt,
    int status){
    
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.url = url;
        this.planStartAt = planStartAt;
        this.planEndAt = planEndAt;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = status;

    }
    
    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getPlanStartAt() {
        return planStartAt;
    }

    public void setPlanStartAt(LocalDate planStartAt) {
        this.planStartAt = planStartAt;
    }

    public LocalDate getPlanEndAt() {
        return planEndAt;
    }

    public void setPlanEndAt(LocalDate planEndAt) {
        this.planEndAt = planEndAt;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
