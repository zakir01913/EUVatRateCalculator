package com.zakir.euvatcalculation.domain.model;

public class VatTypeRate {
    String name;
    double rate;

    public VatTypeRate() {
    }

    public VatTypeRate(String name, double rate) {
        this.name = formatName(name);
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = formatName(name);
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private String formatName(String name) {
        String[] nameArray = name.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : nameArray) {
            stringBuilder.append(s.substring(0, 1).toUpperCase() + s.substring(1)).append(" ");
        }
        return stringBuilder.toString();
    }
}
