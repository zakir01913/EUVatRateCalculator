package com.zakir.euvatcalculation.data.remote;

import com.zakir.euvatcalculation.data.remote.model.EUVatRateCollection;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class EUVatRateDataMapper {

    public List<CountryVatRate> convertToDomain(EUVatRateCollection vatRateCollection) {
        List<CountryVatRate> countryVatRates = new ArrayList<>();
        return countryVatRates;
    }
}
