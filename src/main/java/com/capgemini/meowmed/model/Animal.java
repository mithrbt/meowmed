package com.capgemini.meowmed.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tier")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

}
