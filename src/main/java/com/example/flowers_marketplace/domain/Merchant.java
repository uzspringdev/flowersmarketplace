package com.example.flowers_marketplace.domain;

import com.example.flowers_marketplace.model.Rate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 120)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToMany
    private Set<Role> roles;

    @OneToOne
    private Address address;

    @OneToOne
    private BankAccount bankAccount;

    @Column(name = "rate")
    @Enumerated(EnumType.STRING)
    private Rate rate;

}
