package com.capgemini.meowmed.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class BankDetails {

    private String name;

    private String bank_name;

    private long iban;


}
