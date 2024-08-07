package com.procttor.api.dto;

public class WorkspaceDto {

    private String uuid;
    private String name;
    private String description;
    private String image;
    
    public WorkspaceDto(){
        
    }

    public WorkspaceDto(String uuid, String name, String description, String image){
        this.uuid=uuid;
        this.name=name;
        this.description=description;
        this.image=image;        
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
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
