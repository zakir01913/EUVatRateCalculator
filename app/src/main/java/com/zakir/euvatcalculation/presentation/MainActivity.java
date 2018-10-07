package com.zakir.euvatcalculation.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zakir.euvatcalculation.R;
import com.zakir.euvatcalculation.di.component.DaggerApplicationComponent;
import com.zakir.euvatcalculation.domain.model.CountryVatRate;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements VatCalculatorContact.View {

    @Inject
    VatCalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerApplicationComponent.builder().build().inject(this);
        presenter.setView(this);
    }

    @Override
    public void showProgressLoading() {

    }

    @Override
    public void updateUI(List<CountryVatRate> countryVatRates) {

    }

    @Override
    public void hideProgressLoading() {

    }
}
