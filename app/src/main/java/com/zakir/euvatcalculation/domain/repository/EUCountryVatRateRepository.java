package com.zakir.euvatcalculation.domain.repository;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.Flowable;

public class EUCountryVatRateRepository {

    private final EUCountryVatRateDataSource EUCountryVatRateDataSource;

    public EUCountryVatRateRepository(EUCountryVatRateDataSource EUCountryVatRateDataSource, BaseSchedulerProvider baseSchedulerProvider) {
        this.EUCountryVatRateDataSource = EUCountryVatRateDataSource;
    }

    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        return EUCountryVatRateDataSource.getCountryVatRate();
    }
}