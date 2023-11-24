package com.capgemini.meowmed.model;

import com.capgemini.meowmed.enums.FamilyStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    @Column(name = "bankverbindung")
    private BankDetails bankDetails;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "geburtsdatum")
    private Date birthdate;

    @Embedded
    @Column(name = "adresse")
    private Address address;

    @Column(name = "vorname")
    private String firstname;

   @Column(name = "nachname")
   private String lastname;

   @Column(name = "steuerID")
   private long taxID;

   @Column(name = "svn")
   private String svn;

   @Column(name = "telefonnummer")
   private long telNr;

   /*@Embedded
   @Column(name = "titel")
   private Title title;*/

    @Column(name = "familienstatus")
    private FamilyStatus familyStatus;


    public Person(){}


    public Person(BankDetails bankDetails, Date birthdate, Address address, String firstname, String lastname, long taxID, String svn, long telNr, FamilyStatus familyStatus) {
        this.bankDetails = bankDetails;
        this.birthdate = birthdate;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.taxID = taxID;
        this.svn = svn;
        this.telNr = telNr;
        //this.title = title;
        this.familyStatus = familyStatus;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public long getTaxID() {
        return taxID;
    }

    public void setTaxID(long taxID) {
        this.taxID = taxID;
    }

    public String getSvn() {
        return svn;
    }

    public void setSvn(String svn) {
        this.svn = svn;
    }

    public long getTelNr() {
        return telNr;
    }

    public void setTelNr(long telNr) {
        this.telNr = telNr;
    }

    /*public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }*/

    public FamilyStatus getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }
}
