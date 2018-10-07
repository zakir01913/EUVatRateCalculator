package com.zakir.euvatcalculation.presentation;

import com.zakir.euvatcalculation.domain.model.CountryVatRate;

import java.util.List;

public interface VatCalculatorContact {

    interface View {

        void showProgressLoading();

        void updateUI(List<CountryVatRate> countryVatRates);

        void hideProgressLoading();
    }

    interface Presenter {
        void setView(View view);

        void loadVatData();

        float calculateVat(float amount, float vatPercentage);
    }
}
