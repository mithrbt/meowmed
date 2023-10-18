/* Customer Model Class */

package com.capgemini.meowmed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kunde")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private long id;

    @Column(name = "Vorname")
    private String firstname;

    @Column(name = "Nachname")
    private String lastname;

    @Column(name = "Adresse")
    private String address;

    public Customer(){
    }

    public Customer(String firstname, String lastname, String adress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = adress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
