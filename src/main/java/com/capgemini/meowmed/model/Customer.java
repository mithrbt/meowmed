/* Customer Model Class */

package com.capgemini.meowmed.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "kunde")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int id;

    @Column(name = "Vorname")
    private String firstname;

    @Column(name = "Nachname")
    private String lastname;

    @Column(name = "Adresse")
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Contract> contracts;
    public Customer(){
    }

    public Customer(String firstname, String lastname, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    
}
