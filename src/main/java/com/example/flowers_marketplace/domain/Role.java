package com.example.flowers_marketplace.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "role")
public class Role {
    @Id
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
