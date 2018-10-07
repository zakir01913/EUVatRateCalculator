package com.zakir.euvatcalculation.di.component;

import com.zakir.euvatcalculation.di.module.ContextModule;
import com.zakir.euvatcalculation.di.module.NetworkModule;
import com.zakir.euvatcalculation.di.module.SchedulerModule;
import com.zakir.euvatcalculation.presentation.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class, SchedulerModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
