package com.zakir.euvatcalculation.data.remote;


import com.zakir.euvatcalculation.data.remote.model.EUVatRateCollection;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface EUVatRateApiService {
    @GET
    Flowable<EUVatRateCollection> getEUVatRates();
}
