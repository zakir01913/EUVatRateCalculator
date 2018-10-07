package com.zakir.euvatcalculation.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EUVatRateCollection {
    private String details;
    private String version;
    @SerializedName("rates")
    private List<CountryVatRateData> countryVatRateData;

    public String getDetails() {
        return details;
    }

    public String getVersion() {
        return version;
    }

    public List<CountryVatRateData> getCountryVatRateData() {
        return countryVatRateData;
    }
}
