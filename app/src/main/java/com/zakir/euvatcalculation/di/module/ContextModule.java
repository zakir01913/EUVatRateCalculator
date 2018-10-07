package com.zakir.euvatcalculation.di.module;

import android.content.Context;

import com.zakir.euvatcalculation.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    @ApplicationContext
    public Context context() {
        return context;
    }
}
