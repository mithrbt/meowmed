package com.capgemini.meowmed.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BankDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String bank_name;

    private long iban;

    public BankDetails(String name, String bank_name, long iban) {
        this.name = name;
        this.bank_name = bank_name;
        this.iban = iban;
    }


    public BankDetails() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public long getIban() {
        return iban;
    }

    public void setIban(long iban) {
        this.iban = iban;
    }
}
