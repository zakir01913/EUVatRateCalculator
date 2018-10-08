package com.zakir.euvatcalculation.presentation;

import com.zakir.euvatcalculation.domain.model.VatTypeRate;

import java.util.List;

public interface VatCalculatorContact {

    interface View {

        void showProgressLoading();

        void updateCountrySpinner(List<String> countryList);

        void hideProgressLoading();

        void updateRateSelection(List<VatTypeRate> vatTypeRates);

        void updateTotalAmount(float totalAmount);
    }

    interface Presenter {
        void setView(View view);

        void loadVatData();

        float calculateVat(float amount, float vatPercentage);
    }
}
