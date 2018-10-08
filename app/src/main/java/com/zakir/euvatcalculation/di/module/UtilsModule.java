package com.zakir.euvatcalculation.di.module;

import android.content.Context;

import com.zakir.euvatcalculation.di.ApplicationContext;
import com.zakir.euvatcalculation.utils.DeviceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class UtilsModule {
    @Singleton
    @Provides
    public DeviceUtils deviceUtils(@ApplicationContext Context context) {
        return new DeviceUtils(context);
    }

}
