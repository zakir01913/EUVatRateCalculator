package com.zakir.euvatcalculation.domain.repository;

import com.zakir.euvatcalculation.di.EUVatRateLocalDataSource;
import com.zakir.euvatcalculation.di.EUVatRateRemoteDataSource;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class EUCountryVatRateRepository {

    private final EUCountryVatRateDataSource remoteDataSource;
    private final EUCountryVatRateDataSource localDataSource;

    @Inject
    public EUCountryVatRateRepository(
            @EUVatRateRemoteDataSource EUCountryVatRateDataSource remoteDataSource,
            @EUVatRateLocalDataSource EUCountryVatRateDataSource localDataSource
            ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        return remoteDataSource.getCountryVatRate();
    }
}
