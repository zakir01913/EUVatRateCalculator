package com.zakir.euvatcalculation.data.remote;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateDataSource;

import java.util.List;

import io.reactivex.Flowable;

public class EUCountryVatRateRemoteDataSource implements EUCountryVatRateDataSource {
    private EUVatRateApiService euVatRateApiService;
    private EUVatRateDataMapper euVatRateDataMapper;

    public EUCountryVatRateRemoteDataSource(EUVatRateApiService euVatRateApiService, EUVatRateDataMapper euVatRateDataMapper) {
        this.euVatRateApiService = euVatRateApiService;
        this.euVatRateDataMapper = euVatRateDataMapper;
    }

    @Override
    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        return euVatRateApiService.getEUVatRates().map(euVatRateDataMapper::convertToDomain);
    }
}
