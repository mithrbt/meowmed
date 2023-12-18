package com.capgemini.meowmed.enums;

public enum Profession {
    EMPLOYED("Angestellt"),
    UNEMPLOYED("Arbeitslos"),
    SELFEMPLOYED("Selbstständig"),
    STUDENT("Student");

    public final String label;

    private Profession(String label){
        this.label = label;
    }
}
