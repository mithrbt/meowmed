package com.capgemini.meowmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rasse")
public class Breed {

    @Id
    private String name;

    @Column(name ="min_gewicht")
    private float minWeight;

    @Column(name ="max_gewicht")
    private float maxWeight;

    @Column(name = "min_alter")
    private int minAverageAge;

    @Column(name = "max_alter")
    private int maxAverageAge;

    @Column(name = "krankheitswahrscheinlichkeit")
    private float probabilityOfIllness;

    @OneToMany
    @JsonIgnore
    private List<Cat> cats;

    public Breed(String name, float min_weight,float max_weight, int minAverageAge, int maxAverageAge, float probabilityOfIllness) {
        this.name = name;
        this.minWeight = min_weight;
        this.maxWeight = max_weight;
        this.minAverageAge = minAverageAge;
        this.maxAverageAge = maxAverageAge;
        this.probabilityOfIllness = probabilityOfIllness;
    }

    public Breed() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(float minWeight) {
        this.minWeight = minWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMinAverageAge() {
        return minAverageAge;
    }

    public void setMinAverageAge(int minAverageAge) {
        this.minAverageAge = minAverageAge;
    }

    public int getMaxAverageAge() {
        return maxAverageAge;
    }

    public void setMaxAverageAge(int maxAverageAge) {
        this.maxAverageAge = maxAverageAge;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public float getProbabilityOfIllness() {
        return probabilityOfIllness;
    }

    public void setProbabilityOfIllness(float probabilityOfIllness) {
        this.probabilityOfIllness = probabilityOfIllness;
    }
}
