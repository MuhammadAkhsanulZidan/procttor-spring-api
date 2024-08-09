package com.procttor.api.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    @JsonProperty("id")
    private UUID uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    public UserDto(){
        
    }

    public UserDto(UUID uuid, String name, String email){
        this.uuid = uuid;
        this.name=name;
        this.email=email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
