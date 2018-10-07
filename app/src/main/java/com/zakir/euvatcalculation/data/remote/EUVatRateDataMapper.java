package com.zakir.euvatcalculation.data.remote;

import com.zakir.euvatcalculation.data.remote.model.CountryVatRateData;
import com.zakir.euvatcalculation.data.remote.model.EUVatRateCollection;
import com.zakir.euvatcalculation.data.remote.model.PeriodData;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.model.VatPeriod;
import com.zakir.euvatcalculation.domain.model.VatTypeRate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class EUVatRateDataMapper {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public List<CountryVatRate> convertToDomain(EUVatRateCollection vatRateCollection) throws ParseException {
        List<CountryVatRate> countryVatRates = new ArrayList<>();
        if (vatRateCollection == null)
            return countryVatRates;
        
        for (CountryVatRateData countryVatRateData : vatRateCollection.getCountryVatRateData()) {

            CountryVatRate countryVatRate = new CountryVatRate();
            countryVatRate.setCountryName(countryVatRateData.getCountryName());
            countryVatRate.setCountryCode(countryVatRateData.getCountryCode());
            List<VatPeriod> vatPeriods = new ArrayList<>();

            for (PeriodData periodData : countryVatRateData.getPeriodData()) {

                VatPeriod vatPeriod = new VatPeriod();
                vatPeriod.setEffectFrom(format.parse(periodData.getEffectiveFrom()));
                List<VatTypeRate> vatTypeRates = new ArrayList<>();

                for (Map.Entry<String, Double> rate : periodData.getRates().entrySet()) {
                    VatTypeRate vatTypeRate = new VatTypeRate(rate.getKey(), rate.getValue());
                    vatTypeRates.add(vatTypeRate);
                }
                vatPeriod.setVatTypeRates(vatTypeRates);
                vatPeriods.add(vatPeriod);
            }
            countryVatRate.setVatPeriods(vatPeriods);
            countryVatRates.add(countryVatRate);
        }
        return countryVatRates;
    }
}
