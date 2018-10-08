package com.zakir.euvatcalculation.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.zakir.euvatcalculation.EUVatRateCalculatorApp;
import com.zakir.euvatcalculation.R;
import com.zakir.euvatcalculation.di.component.DaggerApplicationComponent;
import com.zakir.euvatcalculation.domain.model.VatTypeRate;
import com.zakir.euvatcalculation.presentation.exception.ErrorMessageFactory;
import com.zakir.euvatcalculation.presentation.exception.InvalidCurrencyAmountException;
import com.zakir.euvatcalculation.utils.DeviceUtils;
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
    @BindView(R.id.content_group)
    Group contentGroup;
    @BindView(R.id.eu_vat_rate_list_loader_fl)
    FrameLayout progressFrameLayout;
    @BindView(R.id.error_tv)
    TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((EUVatRateCalculatorApp)getApplicationContext()).getApplicationComponent().inject(this);
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
        contentGroup.setVisibility(View.GONE);
        progressFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateCountrySpinner(List<String> countryList) {
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
    }

    @Override
    public void hideProgressLoading() {
        contentGroup.setVisibility(View.VISIBLE);
        progressFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void updateRateSelection(List<VatTypeRate> vatTypeRates) {
        rateRadioGroup.removeAllViews();
        for (int i = 0; i < vatTypeRates.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, (int) (DeviceUtils.getDeviceDensity(this) * 8), 0, 0);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setId(i);
            radioButton.setText(vatTypeRates.get(i).getName());
            radioButton.setTextAppearance(this, android.R.style.TextAppearance_Material_SearchResult_Subtitle);
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
        progressFrameLayout.setVisibility(View.GONE);
        String message = ErrorMessageFactory.create(this, throwable);
        if (throwable instanceof InvalidCurrencyAmountException) {
            textInputEditText.setError(message);
        } else {
            contentGroup.setVisibility(View.GONE);
            progressFrameLayout.setVisibility(View.GONE);
            errorTextView.setText(message);
            errorTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Context getViewContext() {
        return this;
    }

}
