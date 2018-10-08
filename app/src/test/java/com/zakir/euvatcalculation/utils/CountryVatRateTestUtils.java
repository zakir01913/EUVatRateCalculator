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

    public static List<CountryVatRate> getTwoEUVatRateData() {
        List<CountryVatRate> countryVatRates = new ArrayList<>();

        List<VatTypeRate> vatTypeRates = new ArrayList<>();
        vatTypeRates.add(new VatTypeRate("super_reduced", 4));
        vatTypeRates.add(new VatTypeRate("reduced", 10));
        vatTypeRates.add(new VatTypeRate("standard", 20));
        VatPeriod vatPeriod = new VatPeriod(new Date(), vatTypeRates);
        List<VatPeriod> vatPeriods = new ArrayList<>();
        vatPeriods.add(vatPeriod);
        CountryVatRate countryVatRate = new CountryVatRate("Spain", "ES", vatPeriods);
        countryVatRates.add(countryVatRate);

        List<VatTypeRate> vatTypeRates1 = new ArrayList<>();
        vatTypeRates1.add(new VatTypeRate("reduced", 9));
        vatTypeRates1.add(new VatTypeRate("standard", 20));
        VatPeriod vatPeriod1 = new VatPeriod(new Date(), vatTypeRates1);
        List<VatPeriod> vatPeriods1 = new ArrayList<>();
        vatPeriods1.add(vatPeriod1);
        CountryVatRate countryVatRate1 = new CountryVatRate("Bulgaria", "BG", vatPeriods1);
        countryVatRates.add(countryVatRate1);

        return countryVatRates;
    }
}
