package com.capgemini.meowmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "plz")
    private long postleitzahl;

    @Column(name = "straßenname")
    private String streetname;

    @Column(name = "hausnummer")
    private String hausnummer;

    @OneToOne
    @JoinColumn(name = "addressID")
    @JsonIgnore
    private Person person;

}
