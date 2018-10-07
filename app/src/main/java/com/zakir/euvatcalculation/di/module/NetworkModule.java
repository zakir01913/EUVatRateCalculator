package com.zakir.euvatcalculation.di.module;

import android.util.Log;

import com.google.gson.Gson;
import com.zakir.euvatcalculation.data.remote.EUCountryVatRateRemoteDataSource;
import com.zakir.euvatcalculation.data.remote.EUVatRateApiService;
import com.zakir.euvatcalculation.data.remote.EUVatRateDataMapper;
import com.zakir.euvatcalculation.domain.repository.EUCountryVatRateDataSource;

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
    public EUCountryVatRateDataSource countryVatRateRemoteDataSource(EUVatRateApiService euVatRateApiService, EUVatRateDataMapper euVatRateDataMapper) {
        return  new EUCountryVatRateRemoteDataSource(euVatRateApiService, euVatRateDataMapper);
    }
}
