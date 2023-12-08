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

    @Column(name = "e-mail")
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Contract> contracts;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Cat> cats;

    /*@OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Image customerImage;*/

    @Lob
    @Column(name="profile_picture", nullable=false, columnDefinition="mediumblob")
    private byte[] profilePicture;

    public Customer(){
        super();
    }

    public Customer(String email, BankDetails bankDetails, Date birthdate, Address address, String firstname, String lastname, long taxID, String svn, long telNr, FamilyStatus familyStatus, float income, Profession profession, List<Contract> contracts, List<Cat> cats, byte[] profilePicture) {
        super(bankDetails, birthdate, address, firstname, lastname, taxID, svn, telNr, familyStatus);
        this.income = income;
        this.profession = profession;
        this.contracts = contracts;
        this.cats = cats;
        this.email = email;
        this.profilePicture = profilePicture;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
