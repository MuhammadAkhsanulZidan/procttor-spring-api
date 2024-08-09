package com.procttor.api.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "Long")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @OneToMany(mappedBy = "workspace")
    private List<UserWorkspace>userWorkspaces; 

    @OneToMany(mappedBy="workspace", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Project> projects;

    public Workspace(){
        this.uuid=UUID.randomUUID();
    }

    public Workspace(String name, String description, String image){
        this.name=name;
        this.description=description;
        this.image=image;
        this.uuid=UUID.randomUUID();        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
