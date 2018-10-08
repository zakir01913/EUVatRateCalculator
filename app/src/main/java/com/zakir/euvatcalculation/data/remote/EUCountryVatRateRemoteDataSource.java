package com.zakir.euvatcalculation.data.remote;

import android.content.Context;

import com.zakir.euvatcalculation.data.exception.NetworkConnectionException;
import com.zakir.euvatcalculation.di.ApplicationContext;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateDataSource;
import com.zakir.euvatcalculation.utils.DeviceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class EUCountryVatRateRemoteDataSource implements EUCountryVatRateDataSource {
    private EUVatRateApiService euVatRateApiService;
    private EUVatRateDataMapper euVatRateDataMapper;
    private DeviceUtils deviceUtils;

    @Inject
    public EUCountryVatRateRemoteDataSource(
            DeviceUtils deviceUtils,
            EUVatRateApiService euVatRateApiService,
            EUVatRateDataMapper euVatRateDataMapper
    ) {
        this.euVatRateApiService = euVatRateApiService;
        this.euVatRateDataMapper = euVatRateDataMapper;
        this.deviceUtils = deviceUtils ;
    }

    @Override
    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        if (deviceUtils.isThereInternetConnection()) {
            return euVatRateApiService.getEUVatRates().map(euVatRateDataMapper::convertToDomain);
        } else {
            return Flowable.error(new NetworkConnectionException());
        }
    }
}
