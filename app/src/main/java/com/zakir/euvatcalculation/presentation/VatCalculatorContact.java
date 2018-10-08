package com.zakir.euvatcalculation.presentation;

import android.content.Context;

import com.zakir.euvatcalculation.domain.model.VatTypeRate;

import java.math.BigDecimal;
import java.util.List;

public interface VatCalculatorContact {

    interface View {

        void showProgressLoading();

        void updateCountrySpinner(List<String> countryList);

        void hideProgressLoading();

        void updateRateSelection(List<VatTypeRate> vatTypeRates);

        void updateTotalAmount(String totalAmount);

        void showError(Throwable throwable);

        Context getViewContext();
    }

    interface Presenter {
        void setView(View view);

        void loadVatData();

        void onAmountChange(String amount);

        void onRateTypeChange(int rateType);

        void onCountryChange(int countryIndex);
    }
}
