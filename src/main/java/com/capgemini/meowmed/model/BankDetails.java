package com.capgemini.meowmed.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class BankDetails {

    private String accountholder;

    private String bank_name;

    private String iban;

    public BankDetails(String accountholder, String bank_name, String iban) {
        this.accountholder = accountholder;
        this.bank_name = bank_name;
        this.iban = iban;
    }


    public BankDetails() {

    }

    public String getAccountholder() {
        return accountholder;
    }

    public void setAccountholder(String name) {
        this.accountholder = name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
