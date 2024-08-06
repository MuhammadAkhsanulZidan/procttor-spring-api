package com.procttor.api.dto;

public class WorkspaceDto {

    private long id;
    private String name;
    private String description;
    private String image;
    
    public WorkspaceDto(){
        
    }

    public WorkspaceDto(String name, String description, String image){
        this.name=name;
        this.description=description;
        this.image=image;        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
