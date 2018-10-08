package com.zakir.euvatcalculation;

import android.app.Application;

import com.zakir.euvatcalculation.di.component.ApplicationComponent;
import com.zakir.euvatcalculation.di.component.DaggerApplicationComponent;
import com.zakir.euvatcalculation.di.module.ContextModule;

public class EUVatRateCalculatorApp extends Application {
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
