package com.zakir.euvatcalculation.domain.repository;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.Flowable;

public class CountryVatRateRepository {

    private final CountryVatRateDataSource countryVatRateDataSource;

    public CountryVatRateRepository(CountryVatRateDataSource countryVatRateDataSource, BaseSchedulerProvider baseSchedulerProvider) {
        this.countryVatRateDataSource = countryVatRateDataSource;
    }

    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        return countryVatRateDataSource.getCountryVatRate();
    }
}
