package com.zakir.euvatcalculation.domain.model;

public class VatTypeRate {
    String name;
    float rate;

    public VatTypeRate() {
    }

    public VatTypeRate(String name, float rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
