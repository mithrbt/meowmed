/* Customer Model Class */

package com.capgemini.meowmed.model;

import com.capgemini.meowmed.enums.FamilyStatus;
import com.capgemini.meowmed.enums.Profession;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kunde")
public class Customer extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int id;

    @Column(name ="bruttoEinkommen")
    private float income;

    @Column(name = "berufsstand")
    private Profession profession;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Contract> contracts;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Cat> cats;

    public Customer(){
        super();
    }

    public Customer(Date birthdate, Address address, String firstname, String lastname, long taxID, long svn, long telNr, FamilyStatus familyStatus, float income, Profession profession, List<Contract> contracts, List<Cat> cats) {
        super(birthdate, address, firstname, lastname, taxID, svn, telNr, familyStatus);
        this.income = income;
        this.profession = profession;
        this.contracts = contracts;
        this.cats = cats;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
