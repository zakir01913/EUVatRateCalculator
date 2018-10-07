package com.zakir.euvatcalculation.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CountryVatRateData {
    @SerializedName("name")
    private String countryName;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("periods")
    private List<PeriodData> periodData = new ArrayList<>();

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
