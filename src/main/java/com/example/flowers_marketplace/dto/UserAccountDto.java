package com.example.flowers_marketplace.dto;

import com.example.flowers_marketplace.domain.Role;
import com.example.flowers_marketplace.model.UserType;

import java.util.Set;

public class UserAccountDto {
    private Long id;

    private String username;

    private String password;
    private Set<Role> roles;
    private UserType userType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
