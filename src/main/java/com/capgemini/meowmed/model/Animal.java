package com.capgemini.meowmed.model;

import com.capgemini.meowmed.enums.Color;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@MappedSuperclass
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "geburtsdatum")
    private LocalDate birthdate;

    @Column(name = "gewicht")
    private float weight;

    @Column(name = "kastriert")
    private boolean castrated;

    @Column(name = "farbe")
    private Color color;


    public Animal() {
    }

    public Animal(String name, LocalDate birthdate, float weight, boolean castrated, Color color) {
        this.name = name;
        this.birthdate = birthdate;
        this.weight = weight;
        this.castrated = castrated;
        this.color = color;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
