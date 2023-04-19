package com.example.flowers_marketplace.domain;

import com.example.flowers_marketplace.model.UserType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_account")
public class UserAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customers_roles",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<Role> roles;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserAccount() {
    }

    public UserAccount(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public UserAccount(String username, String password, Set<Role> roles, UserType userType) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userType = userType;
    }

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
