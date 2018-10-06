package com.zakir.euvatcalculation.domain.repository;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;

import java.util.List;

import io.reactivex.Flowable;

public interface CountryVatRateDataSource {
    Flowable<List<CountryVatRate>> getCountryVatRate();
}
