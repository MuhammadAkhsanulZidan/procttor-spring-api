package com.procttor.api.dto;

import java.util.UUID;

public class WorkspaceDto {

    private UUID uuid;
    private String name;
    private String description;
    private String image;
    
    public WorkspaceDto(){
        
    }

    public WorkspaceDto(UUID uuid, String name, String description, String image){
        this.uuid=uuid;
        this.name=name;
        this.description=description;
        this.image=image;        
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
