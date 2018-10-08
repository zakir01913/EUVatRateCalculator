package com.zakir.euvatcalculation.di.component;

import com.zakir.euvatcalculation.di.module.ContextModule;
import com.zakir.euvatcalculation.di.module.NetworkModule;
import com.zakir.euvatcalculation.di.module.SchedulerModule;
import com.zakir.euvatcalculation.di.module.UtilsModule;
import com.zakir.euvatcalculation.presentation.MainActivity;
import com.zakir.euvatcalculation.utils.DeviceUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class, SchedulerModule.class, UtilsModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
