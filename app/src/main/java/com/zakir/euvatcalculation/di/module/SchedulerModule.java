package com.zakir.euvatcalculation.di.module;

import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProvider;
import com.zakir.euvatcalculation.presentation.schedulers.BaseSchedulerProviderImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Singleton
    @Provides
    public BaseSchedulerProvider baseSchedulerProvider() {
        return new BaseSchedulerProviderImpl();
    }
}
