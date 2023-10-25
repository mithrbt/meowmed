package com.capgemini.meowmed.model;

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
    private float coverage;


    public Contract() {

    }

    public Contract(Date start, Date end, float coverage) {
        this.start = start;
        this.end = end;
        this.coverage = coverage;
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

    public float getCoverage() {
        return coverage;
    }

    public void setCoverage(float coverage) {
        this.coverage = coverage;
    }
}
