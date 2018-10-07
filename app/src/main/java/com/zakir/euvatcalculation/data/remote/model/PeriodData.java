package com.zakir.euvatcalculation.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PeriodData {
    @SerializedName("effective_from")
    private String effectiveFrom;
    private Map<String, Double> rates;

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
