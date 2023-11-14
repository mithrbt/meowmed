package com.capgemini.meowmed.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bankverbindung")
    private long bank_details;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "geburtsdatum")
    private Date birthdate;




}
