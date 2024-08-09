package com.procttor.api.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectDto {

    private UUID uuid;
    private String name;
    private String description;
    private String image;

    @JsonProperty("plan_start_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planStartAt;

    @JsonProperty("plan_end_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planEndAt;

    @JsonProperty("start_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startAt;

    @JsonProperty("end_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endAt;

    private int status;

    public ProjectDto(){

    }

    public ProjectDto(UUID uuid, 
        String name, 
        String description,
        String image,
        LocalDate planStartAt,
        LocalDate planEndAt,
        LocalDate startAt,
        LocalDate endAt,
        int status){
        
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.image = image;
        this.planStartAt = planStartAt;
        this.planEndAt = planEndAt;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = status;

    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public LocalDate getPlanStartAt() {
        return planStartAt;
    }

    public LocalDate getPlanEndAt() {
        return planEndAt;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public int getStatus() {
        return status;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlanStartAt(LocalDate planStartAt) {
        this.planStartAt = planStartAt;
    }

    public void setPlanEndAt(LocalDate planEndAt) {
        this.planEndAt = planEndAt;
    }
    
    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
