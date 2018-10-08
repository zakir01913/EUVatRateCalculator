package com.zakir.euvatcalculation.data.local;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class EUCountryVatRateLocalDataSource implements EUCountryVatRateDataSource {
    @Override
    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        return Flowable.empty();
    }
}
