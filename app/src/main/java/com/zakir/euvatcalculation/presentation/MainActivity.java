package com.zakir.euvatcalculation.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.zakir.euvatcalculation.R;
import com.zakir.euvatcalculation.di.component.DaggerApplicationComponent;
import com.zakir.euvatcalculation.domain.model.VatTypeRate;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements VatCalculatorContact.View {

    @Inject
    VatCalculatorPresenter presenter;

    @BindView(R.id.country_sp)
    Spinner countrySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerApplicationComponent.builder().build().inject(this);
        presenter.setView(this);
        presenter.loadVatData();
    }

    @Override
    public void showProgressLoading() {

    }

    @Override
    public void updateCountrySpinner(List<String> countryList) {
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
    }

    @Override
    public void hideProgressLoading() {

    }

    @Override
    public void updateRateSelection(List<VatTypeRate> vatTypeRates) {

    }

    @Override
    public void updateTotalAmount(float totalAmount) {

    }

}
