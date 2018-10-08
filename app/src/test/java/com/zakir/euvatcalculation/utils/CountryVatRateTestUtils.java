package com.zakir.euvatcalculation.utils;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.model.VatPeriod;
import com.zakir.euvatcalculation.domain.model.VatTypeRate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CountryVatRateTestUtils {
    public static List<CountryVatRate> getEmptyList() {
        return new ArrayList<CountryVatRate>();
    }

    public static List<CountryVatRate> getDefaultVatData() {
        List<VatTypeRate> vatTypeRates = new ArrayList<>();
        vatTypeRates.add(new VatTypeRate("reduced", 10));
        vatTypeRates.add(new VatTypeRate("standard", 20));
        VatPeriod vatPeriod = new VatPeriod(new Date(), vatTypeRates);
        List<VatPeriod> vatPeriods = new ArrayList<>();
        vatPeriods.add(vatPeriod);
        CountryVatRate countryVatRate = new CountryVatRate("Spain", "ES", vatPeriods);
        List<CountryVatRate> countryVatRates = new ArrayList<>();
        countryVatRates.add(countryVatRate);
        return countryVatRates;
    }
}
