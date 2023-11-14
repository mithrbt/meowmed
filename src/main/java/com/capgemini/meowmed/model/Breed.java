package com.capgemini.meowmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Breed {

    @Id
    private String name;

    @Column(name ="norm_gewicht")
    private float standard_weight;

    @Column(name = "norm_alter")
    private int standard_age;

    @Column(name = "krankheitswahrscheinlichkeit")
    private float probabilityOfIllness;

    @OneToMany
    @JsonIgnore
    private List<Cat> cats;

    public Breed(String name, float standard_weight, int standard_age, float probabilityOfIllness) {
        this.name = name;
        this.standard_weight = standard_weight;
        this.standard_age = standard_age;
        this.probabilityOfIllness = probabilityOfIllness;
    }

    public Breed() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStandard_weight() {
        return standard_weight;
    }

    public void setStandard_weight(float standard_weight) {
        this.standard_weight = standard_weight;
    }

    public int getStandard_age() {
        return standard_age;
    }

    public void setStandard_age(int standard_age) {
        this.standard_age = standard_age;
    }

    public float getProbabilityOfIllness() {
        return probabilityOfIllness;
    }

    public void setProbabilityOfIllness(float probabilityOfIllness) {
        this.probabilityOfIllness = probabilityOfIllness;
    }
}
