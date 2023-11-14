package com.capgemini.meowmed.model;

import com.capgemini.meowmed.enums.Environment;
import com.capgemini.meowmed.enums.Personality;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
    @JoinColumn(name= "breed_name")
    @JsonIgnore
    private Breed breed;

    public Cat(Personality personality, Environment environment, Contract contract) {
        super();
        this.personality = personality;
        this.environment = environment;
        this.contract = contract;
    }

    public Cat() {
        super();
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
