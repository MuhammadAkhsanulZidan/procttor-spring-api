package com.procttor.api.dto;

public class JwtTokenDto {
    private String token;

    public JwtTokenDto(String token){
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
