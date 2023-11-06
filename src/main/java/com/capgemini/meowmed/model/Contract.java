package com.capgemini.meowmed.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    //Jeder Vertrag kann nur einem Kunden zugeordnet werden, ein Kunde kann mehrere Vertäge haben
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    private Cat cat;

    public Contract() {

    }

    public Contract(Date start, Date end, int coverage, Customer customer) {
        this.start = start;
        this.end = end;
        this.coverage = coverage;
        this.customer = customer;
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
