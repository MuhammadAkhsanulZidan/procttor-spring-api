package com.procttor.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public class ActivityDto {
    private UUID uuid;
    private String name;
    private String description;
    private String url;
    private LocalDate planStartAt;
    private LocalDate planEndAt;
    private LocalDate startAt;
    private LocalDate endAt;
    private int status;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
}
