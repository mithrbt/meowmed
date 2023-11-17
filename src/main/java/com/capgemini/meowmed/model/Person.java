package com.capgemini.meowmed.model;

import com.capgemini.meowmed.enums.Marital_status;
import com.capgemini.meowmed.enums.Titel;
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

    @Column(name = "vorname")
    private long firstname;

    @Column(name = "nachname")
    private long surname;

    @Column(name = "sozialversicherungsnummer")
    private String svn;

    @Column(name = "telefonnummer")
    private long phone_number;

    @Column(name = "adresse")
    private long addressID;

    @Enumerated(EnumType.ORDINAL)
    private Titel titel;

    @Enumerated(EnumType.ORDINAL)
    private Marital_status marital_status;


}
