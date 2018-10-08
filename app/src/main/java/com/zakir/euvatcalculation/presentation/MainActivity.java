package com.zakir.euvatcalculation.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.zakir.euvatcalculation.R;
import com.zakir.euvatcalculation.di.component.DaggerApplicationComponent;
import com.zakir.euvatcalculation.domain.model.VatTypeRate;
import com.zakir.euvatcalculation.utils.EditTextCurrencyInputFilter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements VatCalculatorContact.View {

    @Inject
    VatCalculatorPresenter presenter;

    @BindView(R.id.country_sp)
    Spinner countrySpinner;
    @BindView(R.id.rate_rg)
    RadioGroup rateRadioGroup;
    @BindView(R.id.total_amount_tv)
    TextView totalAmountTextView;
    @BindView(R.id.currency_et)
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerApplicationComponent.builder().build().inject(this);
        presenter.setView(this);
        presenter.loadVatData();

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.onCountryChange(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rateRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> presenter.onRateTypeChange(i));
        textInputEditText.setFilters(new InputFilter[]{ new EditTextCurrencyInputFilter()});
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onAmountChange(editable.toString());
            }
        });
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
        rateRadioGroup.removeAllViews();
        for (int i = 0; i < vatTypeRates.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setId(i);
            radioButton.setText(vatTypeRates.get(i).getName());
            if (i == 0) {
                radioButton.setChecked(true);
            }
            rateRadioGroup.addView(radioButton);
        }
    }

    @Override
    public void updateTotalAmount(String totalAmount) {
        totalAmountTextView.setText(totalAmount);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public Context getViewContext() {
        return this;
    }

}
