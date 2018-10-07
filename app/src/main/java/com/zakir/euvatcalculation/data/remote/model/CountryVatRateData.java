package com.zakir.euvatcalculation.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryVatRateData {
    @SerializedName("name")
    private String countryName;
    @SerializedName("country_code")
    private String countryCode;
    private List<PeriodData> periodData;

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<PeriodData> getPeriodData() {
        return periodData;
    }
}
