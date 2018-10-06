package com.zakir.euvatcalculation.domain.model;

import java.util.List;

public class CountryVatRate {
    String countryName;
    String countryCode;
    List<VatPeriod> vatPeriods;

    public CountryVatRate() {
    }

    public CountryVatRate(String countryName, String countryCode, List<VatPeriod> vatPeriods) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.vatPeriods = vatPeriods;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<VatPeriod> getVatPeriods() {
        return vatPeriods;
    }

    public void setVatPeriods(List<VatPeriod> vatPeriods) {
        this.vatPeriods = vatPeriods;
    }
}
