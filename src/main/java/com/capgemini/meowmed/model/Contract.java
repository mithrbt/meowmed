package com.capgemini.meowmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Vertrag")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "beginn")
    private Date start;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "ende")
    private Date end;

    @Column(name = "deckung")
    private int coverage;

    @Column(name = "monatlBeitrag")
    private double quote;

    //Jeder Vertrag kann nur einem Kunden zugeordnet werden, ein Kunde kann mehrere Vertäge haben
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne (mappedBy = "contract")
    private Cat cat;

    public Contract() {

    }

    public Contract(Date start, Date end, int coverage, Customer customer, double quote) {
        this.start = start;
        this.end = end;
        this.coverage = coverage;
        this.customer = customer;
        this.quote = quote;
    }

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }
}
