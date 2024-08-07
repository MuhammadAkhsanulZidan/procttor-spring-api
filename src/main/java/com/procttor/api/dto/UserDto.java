package com.procttor.api.dto;

import java.util.UUID;

public class UserDto {
    private String uuid;
    private String name;
    private String email;

    public UserDto(){
        
    }

    public UserDto(String uuid, String name, String email){
        this.uuid = uuid;
        this.name=name;
        this.email=email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
