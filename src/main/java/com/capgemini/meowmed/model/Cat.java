package com.capgemini.meowmed.model;

import com.capgemini.meowmed.enums.Color;
import com.capgemini.meowmed.enums.Environment;
import com.capgemini.meowmed.enums.Personality;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Katze")
public class Cat extends Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Personality personality;

    @Enumerated(EnumType.ORDINAL)
    private Environment environment;

    @OneToOne
    @JoinColumn(name = "contract_id")
    @JsonIgnore
    private Contract contract;

    @ManyToOne
    @JoinColumn(name= "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name= "breed_id")
    @JsonIgnore
    private Breed breed;


    public Cat(String name, LocalDate birthdate, float weight, boolean castrated, Color color, Personality personality, Environment environment, Contract contract, Customer customer, Breed breed) {
        super(name, birthdate, weight, castrated, color);
        this.personality = personality;
        this.environment = environment;
        this.contract = contract;
        this.customer = customer;
        //this.breed = breed;
    }

    public Cat() {
        super();
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personality getPersonality() {
        return personality;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
