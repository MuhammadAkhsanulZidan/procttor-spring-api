package com.procttor.api.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.procttor.api.model.User;

public class CustomUserDetails implements UserDetails{

    private final User user;
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user){
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = Collections.emptyList();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User getUser() {
        return user;
    }

}
