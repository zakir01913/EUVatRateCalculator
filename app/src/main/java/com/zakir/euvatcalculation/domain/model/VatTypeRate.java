package com.zakir.euvatcalculation.domain.model;

public class VatTypeRate {
    String name;
    double rate;

    public VatTypeRate() {
    }

    public VatTypeRate(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
