package com.example.flowers_marketplace.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    private Customer customer;

    @OneToMany
    private List<Flower> flowers;
}
