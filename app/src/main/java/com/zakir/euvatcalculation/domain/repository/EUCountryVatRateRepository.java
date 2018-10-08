package com.zakir.euvatcalculation.domain.repository;

import android.content.Context;

import com.zakir.euvatcalculation.data.exception.NetworkConnectionException;
import com.zakir.euvatcalculation.di.ApplicationContext;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;
import com.zakir.euvatcalculation.utils.DeviceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class EUCountryVatRateRepository {

    private final EUCountryVatRateDataSource EUCountryVatRateDataSource;
    private final Context context;

    @Inject
    public EUCountryVatRateRepository(@ApplicationContext Context context, EUCountryVatRateDataSource EUCountryVatRateDataSource) {
        this.EUCountryVatRateDataSource = EUCountryVatRateDataSource;
        this.context = context;
    }

    public Flowable<List<CountryVatRate>> getCountryVatRate() {
        if (DeviceUtils.isThereInternetConnection(context)){
            return EUCountryVatRateDataSource.getCountryVatRate();
        } else {
            return Flowable.error(new NetworkConnectionException());
        }
    }
}
