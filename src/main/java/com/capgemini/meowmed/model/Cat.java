package com.capgemini.meowmed.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Katze")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Personality personality;

    @Enumerated(EnumType.ORDINAL)
    private Environment environment;

}
