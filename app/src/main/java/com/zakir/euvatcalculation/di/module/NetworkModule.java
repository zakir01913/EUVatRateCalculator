package com.zakir.euvatcalculation.di.module;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zakir.euvatcalculation.data.local.EUCountryVatRateLocalDataSource;
import com.zakir.euvatcalculation.data.remote.EUCountryVatRateRemoteDataSource;
import com.zakir.euvatcalculation.data.remote.EUVatRateApiService;
import com.zakir.euvatcalculation.data.remote.EUVatRateDataMapper;
import com.zakir.euvatcalculation.di.ApplicationContext;
import com.zakir.euvatcalculation.di.EUVatRateLocalDataSource;
import com.zakir.euvatcalculation.di.EUVatRateRemoteDataSource;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateDataSource;
import com.zakir.euvatcalculation.utils.DeviceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public HttpLoggingInterceptor loggingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d("Retrofit", message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor)

    {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson)

    {
        return new Retrofit.Builder()
                .baseUrl("https://jsonvat.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    @Provides
    @Singleton
    public EUVatRateApiService conversationsApiService(Retrofit retrofit) {
        return retrofit.create(EUVatRateApiService.class);
    }

    @Singleton
    @Provides
    @EUVatRateRemoteDataSource
    public EUCountryVatRateDataSource countryVatRateRemoteDataSource(DeviceUtils deviceUtils, EUVatRateApiService euVatRateApiService, EUVatRateDataMapper euVatRateDataMapper) {
        return  new EUCountryVatRateRemoteDataSource(deviceUtils, euVatRateApiService, euVatRateDataMapper);
    }

    @Singleton
    @Provides
    @EUVatRateLocalDataSource
    public EUCountryVatRateDataSource countryVatRateLocalDataSource() {
        return  new EUCountryVatRateLocalDataSource();
    }
}
