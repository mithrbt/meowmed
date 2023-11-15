package com.capgemini.meowmed.model;

import jakarta.persistence.Embeddable;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;

@Embeddable
public class Address {

    private String country;
    private String city;
    private int zipCode;
    private String street;
    private String houseNr;

    public Address(){}
    public Address(String country, String city, int zipCode, String street, String houseNr) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNr = houseNr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }
}
